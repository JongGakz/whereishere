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
                             @PageableDefault(page = 0, size = 7, sort = "eventPostWriteDate", direction = Sort.Direction.DESC) Pageable pageable){
        Page<EventPost> events = eventPostService.eventPostList(pageable);
        eventPostService.page(model, pageable, events);
        return "eventPost/eventPosts";
    }

    // 검색하기
    @GetMapping("/eventPost/search")
    public String eventPostsSearch(Model model, Pageable pageable, String searchKeyword){
        Page<EventPost> events = eventPostService.eventPostSearchList(searchKeyword, pageable);
        eventPostService.page(model, pageable, events);
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

        eventPostService.page(model, pageable, resultPage);
        return "eventPost/eventPostMyList";
    }

    //이벤트 포스트 생성 메소드
    @GetMapping("/eventPost/new")
    public String showEventPostForm(RedirectAttributes redirectAttributes, @AuthenticationPrincipal User user) {
        Company findCompany = companyServiceImpl.findCompanyByLoginId(user.getUsername());
        List<EventPost> all = eventPostService.findAll();
        List<EventPost> collect = all.stream().filter(n -> n.getCompany().equals(findCompany)).collect(Collectors.toUnmodifiableList());
        //필터 equals를 사용하면 그 것만 보여준다 collect는 다시 리스트화 시켜준다.
        if(collect.isEmpty()) {
            return "eventPost/createEventPost";
        } else {
            redirectAttributes.addAttribute("eventPostId", collect.get(0).getEventPostId());
            return "redirect:/events/eventPost/update/{eventPostId}";
        }
    }

    //이벤트 게시글을 작성하고 등록했을 때, eventPost에 객체로 저장하는 메서드
    @PostMapping("/eventPost/new")
    public String saveEventPost(@RequestParam String eventPostTitle,
                                @RequestParam String eventPostContent, @RequestParam(name = "eventPostImgs", required = false) MultipartFile eventPostImgs,
                                @AuthenticationPrincipal User user) throws Exception {
        Company findCompany = companyServiceImpl.findCompanyByLoginId(user.getUsername());


            String eventPostImgName = UUID.randomUUID() + ".png";
            eventPostImgs.transferTo(new File(fileStore.getFullPath(eventPostImgName)));
            eventPostService.save(new EventPost(findCompany, eventPostTitle, eventPostContent, eventPostImgName, LocalDate.now()));

        return "redirect:/events/eventPost";
    }

    //제목을 누르면 상세 페이지로 넘어가는 매소드
    @GetMapping("/eventPost/{eventPostId}")
    public String showEventPost(@PathVariable Long eventPostId, Model model,@AuthenticationPrincipal User user) {
        eventPostService.eventView(eventPostId, model, user);
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
    @GetMapping("/eventPost/update/{eventPostId}")
    public String eventPostUpdateForm(@PathVariable("eventPostId") Long eventPostId, Model model) {
        model.addAttribute("eventPost", eventPostService.eventPostView(eventPostId));
        return "eventPost/eventPostModify";
    }

    @PostMapping("/eventPost/update/{eventPostId}")
    public String eventPostUpdate(@PathVariable("eventPostId") Long eventPostId, @RequestParam String eventPostTitle,
                                  @RequestParam String eventPostContent, @RequestParam("eventPostImgs") MultipartFile eventPostImgs) throws IOException {
        eventPostService.eventPostModify(eventPostId, eventPostTitle, eventPostContent, eventPostImgs);
        return "redirect:/events/eventPost";
    }
}
