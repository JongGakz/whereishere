package twogtwoj.whereishere.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.EventPost;
import twogtwoj.whereishere.file.FileStore;
import twogtwoj.whereishere.service.CompanyServiceImpl;
import twogtwoj.whereishere.service.EventPostService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventPostController {
    /*
    1. 이벤트 게시판 메인
    2. 내가 쓴 글 보기
    3. 글쓰기
    4. 게시물 상세보기
     */
    private final EventPostService eventPostService;
    private final CompanyServiceImpl companyServiceImpl;
    private final FileStore fileStore;

    // 이벤트포스트 리스트화 하여 보여주는 메소드
    @GetMapping("/eventPost")
    public String eventPosts(Model model,
                             @PageableDefault(page = 0, size = 5, sort = "eventPostWriteDate", direction = Sort.Direction.DESC) Pageable pageable){
//        String companyName = "하얀나라피부과의원";
        Page<EventPost> events = eventPostService.eventPostList(pageable);

        int nowPage = events.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, events.getTotalPages());
        model.addAttribute("events", events);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "eventPost/eventPosts";
    }

    // 검색하기
    @GetMapping("/eventPost/search")
    public String eventPostsSearch(Model model, Pageable pageable, String searchKeyword){
        Page<EventPost> events = eventPostService.eventPostSearchList(searchKeyword, pageable);
        int nowPage = events.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, events.getTotalPages());
        model.addAttribute("events", events);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "eventPost/eventPosts";
    }


    // 내가 쓴글 보기
    @GetMapping("/eventPost/myList")
    public String eventPostsList(Model model, Pageable pageable, @AuthenticationPrincipal User user) {
        Company company = companyServiceImpl.findCompanyByLoginId(user.getUsername());
        Page<EventPost> events = eventPostService.eventPostList(pageable);

        Page<EventPost> resultPage = PageableExecutionUtils.getPage(
                events.filter(n -> n.getCompany().equals(company))
                        .stream().collect(Collectors.toList()), pageable,
                () -> events.filter(n -> n.getCompany().equals(company)).stream().count());

        int nowPage = resultPage.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, resultPage.getTotalPages());
        model.addAttribute("events", resultPage);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "eventPost/eventPostMyList";
    }

    //이벤트 포스트 생성 메소드
    @GetMapping("/eventPost/new")
    public String showEventPostForm(RedirectAttributes redirectAttributes, @AuthenticationPrincipal User user) {
        Company findCompany = companyServiceImpl.findCompanyByLoginId(user.getUsername());
        List<EventPost> all = eventPostService.findAll();
        List<EventPost> collect = all.stream().filter(n -> n.getCompany().equals(findCompany)).collect(Collectors.toUnmodifiableList()); //필터 equals를 사용하면 그 것만 보여준다 collect는 다시 리스트화 시켜준다.
        if(collect.isEmpty()) {
            return "eventPost/createEventPost";
        } else {
            redirectAttributes.addAttribute("eventPostId", collect.get(0).getEventPostId());
            return "redirect:/events/eventPost/modify/{eventPostId}";
        }

    }

    //이벤트 게시글을 작성하고 등록했을 때, eventPost에 객체로 저장하는 메서드
    @PostMapping("/eventPost/new")
    public String saveEventPost(@RequestParam String eventPostTitle,
                                @RequestParam String eventPostContent, @RequestParam(name = "eventPostImg1", required = false) MultipartFile eventPostImg1,
                                @RequestParam(name = "eventPostImg2", required = false) MultipartFile eventPostImg2, @AuthenticationPrincipal User user) throws IOException {
        Company findCompany = companyServiceImpl.findCompanyByLoginId(user.getUsername());

        if(eventPostImg1 == null && eventPostImg2 == null) {
            eventPostService.save(new EventPost(findCompany, eventPostTitle, eventPostContent, null, null, LocalDate.now()));
        } else if (eventPostImg1 != null && eventPostImg2 == null) {
            String eventPostImg1Name = UUID.randomUUID() + ".png";
            eventPostImg1.transferTo(new File(fileStore.getFullPath(eventPostImg1Name)));
            eventPostService.save(new EventPost(findCompany, eventPostTitle, eventPostContent, eventPostImg1Name, null, LocalDate.now()));
        } else if (eventPostImg1 == null && eventPostImg2 != null) {
            String eventPostImg2Name = UUID.randomUUID() + ".png";
            eventPostImg2.transferTo(new File(fileStore.getFullPath(eventPostImg2Name)));
            eventPostService.save(new EventPost(findCompany, eventPostTitle, eventPostContent, null, eventPostImg2Name, LocalDate.now()));
        }else {
            String eventPostImg1Name = UUID.randomUUID() + ".png";
            String eventPostImg2Name = UUID.randomUUID() + ".png";
            eventPostImg1.transferTo(new File(fileStore.getFullPath(eventPostImg1Name)));
            eventPostImg2.transferTo(new File(fileStore.getFullPath(eventPostImg2Name)));
            eventPostService.save(new EventPost(findCompany, eventPostTitle, eventPostContent, eventPostImg1Name, eventPostImg2Name, LocalDate.now()));
        }
        return "redirect:/events/eventPost";
    }

    //제목을 누르면 상세 페이지로 넘어가는 매소드
    @GetMapping("/eventPost/{eventPostId}")
    public String showEventPost(@PathVariable Long eventPostId, Model model,@AuthenticationPrincipal User user) {
        EventPost eventPosts = eventPostService.eventPostView(eventPostId);
        model.addAttribute("eventPost", eventPosts);
        if(user.getAuthorities().stream().filter(n -> n.getAuthority().equals("ROLE_COMPANY")).toArray().length == 1){
            Company findCompany = companyServiceImpl.findCompanyByLoginId(user.getUsername());
            model.addAttribute("myCompany", findCompany.getName());
        } else {
            model.addAttribute("myCompany","");
        }
        return "eventPost/eventPostView";
    }

    //이미지를 볼 수 있게 해주는 매소드
    @ResponseBody
    @GetMapping("/image/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    // 특정 게시글 삭제
    @GetMapping("/eventPost/delete")
    public String eventPostDelete(Long eventPostId) {
        eventPostService.eventPostDelete(eventPostId);
        return "redirect:/events/eventPost";
    }

    // 특젙 게시글 수정
    @GetMapping("/eventPost/modify/{eventPostId}")
    public String eventPostModify(@PathVariable("eventPostId") Long eventPostId, Model model) {
        model.addAttribute("eventPost", eventPostService.eventPostView(eventPostId));
        return "eventPost/eventPostModify";
    }

    @PostMapping("/eventPost/update/{eventPostId}")
    public String eventPostUpdate(@PathVariable("eventPostId") Long eventPostId, @RequestParam String eventPostTitle,
                                  @RequestParam String eventPostContent, @RequestParam MultipartFile eventPostImg1,
                                  @RequestParam MultipartFile eventPostImg2) throws IOException {
        EventPost post = eventPostService.eventPostView(eventPostId);

        post.setEventPostTitle(eventPostTitle);
        post.setEventPostContent(eventPostContent);

        String eventPostMainImgName = UUID.randomUUID() + ".png";
        String eventPostImgName = UUID.randomUUID() + ".png";
        eventPostImg1.transferTo(new File(fileStore.getFullPath(eventPostMainImgName)));
        eventPostImg2.transferTo(new File(fileStore.getFullPath(eventPostImgName)));

        post.setEventPostImg1(eventPostMainImgName);
        post.setEventPostImg2(eventPostImgName);
        post.setEventPostWriteDate(LocalDate.now());

        eventPostService.save(post);
        return "redirect:/events/eventPost";
    }
}
