package twogtwoj.whereishere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.EventPost;
import twogtwoj.whereishere.file.FileStore;
import twogtwoj.whereishere.repository.EventPostRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventPostService {

    private final EventPostRepository eventPostRepository;
    private final CompanyServiceImpl companyServiceImpl;
    private final FileStore fileStore;



    public void save(EventPost eventPost){
        eventPostRepository.save(eventPost);
    }

    // 게시글 리스트 처리
    public Page<EventPost> eventPostList(Pageable pageable) {
        return eventPostRepository.findAll(pageable);
    }

    public List<EventPost> findAll() {
        return eventPostRepository.findAll();
    }

    public EventPost eventPostView(Long eventPostId) {
        return eventPostRepository.findById(eventPostId).get();
    }


    //검색
    public Page<EventPost> eventPostSearchList(String searchContent, Pageable pageable) {
        Page<EventPost> events = eventPostList(pageable);

        Page<EventPost> eventPosts = PageableExecutionUtils.getPage(
                events.filter(n -> n.getCompany().getName().contains(searchContent))
                        .stream().collect(Collectors.toList()), pageable,
                () -> events.filter(n -> n.getCompany().getName().contains(searchContent)).stream().count());
        return eventPosts;
    }

    //삭제
    public void eventPostDelete(Long eventPostId) {
        EventPost eventPost = eventPostRepository.findById(eventPostId).get();
        eventPostRepository.delete(eventPost);
    }


    //에벤트포스트 상세페이지
    public void eventView(@PathVariable Long eventPostId, Model model, @AuthenticationPrincipal User user) {
        EventPost eventPosts = eventPostView(eventPostId);
        String[] imgs = eventPosts.getEventPostImgs().split(", ");
        model.addAttribute("imgs", imgs);
        model.addAttribute("eventPost", eventPosts);
        if(user.getAuthorities().stream().filter(n -> n.getAuthority().equals("ROLE_COMPANY")).toArray().length == 1){
            Company findCompany = companyServiceImpl.findCompanyByLoginId(user.getUsername());
            model.addAttribute("myCompany", findCompany.getName());
        } else {
            model.addAttribute("myCompany","");
        }
    }

    public void eventPostModify(@PathVariable("eventPostId") Long eventPostId, @RequestParam String eventPostTitle,
                                @RequestParam String eventPostContent, @RequestParam("eventPostImgs") MultipartFile eventPostImgs) throws IOException {
        EventPost post = eventPostView(eventPostId);

        post.setEventPostTitle(eventPostTitle);
        post.setEventPostContent(eventPostContent);

        File file = new File(fileStore.getFullPath(post.getEventPostImgs()));
        file.delete();

        String eventPostImgName = UUID.randomUUID() + ".png";
        eventPostImgs.transferTo(new File(fileStore.getFullPath(eventPostImgName)));

        post.setEventPostImgs(eventPostImgName);
        post.setEventPostWriteDate(LocalDate.now());
        save(post);
    }

    public void page(Model model,
                     @PageableDefault(page = 0, size = 7, sort = "eventPostWriteDate", direction = Sort.Direction.DESC) Pageable pageable, Page<EventPost> events) {
        int nowPage = events.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, events.getTotalPages());

        if(endPage < startPage)
            endPage = nowPage;

        model.addAttribute("events", events);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
    }

}
