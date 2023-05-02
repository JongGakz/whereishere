package twogtwoj.whereishere.web;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import twogtwoj.whereishere.domain.Member;
import twogtwoj.whereishere.domain.ReviewPost;
import twogtwoj.whereishere.dto.ReviewPostDto;
import twogtwoj.whereishere.file.FileStore;
import twogtwoj.whereishere.repository.ReviewPostRepository;
import twogtwoj.whereishere.service.MemberService;
import twogtwoj.whereishere.service.ReviewPostService;

import java.net.MalformedURLException;
import java.security.Principal;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewPostController {

    private final ReviewPostService reviewPostService;

    private final MemberService memberService;

    private final FileStore fileStore;

    @GetMapping("/post")
    public String reviewPostForm(Model model) {

        model.addAttribute("review", new ReviewPostDto());
        return "/review/reviewPost";
    }

    @GetMapping("/view/{reviewPostId}")
    public String image(@PathVariable Long reviewPostId, Model model) {
        ReviewPost reviewPost = reviewPostService.reviewPostView(reviewPostId);
        model.addAttribute("image", reviewPost);
        return "review/view";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }
    @RequestMapping(path = "/postPro", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String reviewPostPro(@ModelAttribute ReviewPostDto reviewPostDto, Model model, @AuthenticationPrincipal User user) throws Exception{
        Member member = memberService.findMemberByLoginId(user.getUsername());
        reviewPostService.post(reviewPostDto,member);

        model.addAttribute("message", "작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/review/list");

        return "/review/message";
    }

    @SneakyThrows
    @GetMapping("/list")
    public String reviewList(@PageableDefault(size = 10, sort = "reviewPostId", direction = Sort.Direction.DESC) Pageable pageable,
                             @RequestParam(name = "searchKeyword", required = false) String searchKeyword,
                             @RequestParam(name = "name", required = false) String name,
                             Model model) {

        Page<ReviewPost> list = null;

        if (searchKeyword == null && name == null) {
            list = reviewPostService.reviewPostList(pageable);
        } else if (searchKeyword != null && name != null) {
            list = reviewPostService.reviewPostSearchList(searchKeyword, name, pageable);
        } else if (searchKeyword != null) {
            list = reviewPostService.reviewPostSearchList(searchKeyword, null, pageable);
        } else if (name != null) {
            list = reviewPostService.reviewPostSearchList(null, name, pageable);
        } else {
            list = reviewPostService.reviewPostList(pageable);
        }

        //현재 페이지
        int nowPage = list.getPageable().getPageNumber() + 1; //list 변수 없이 사용해도 가능하지만 통일성을 위해서 사용한 것. 시작이 0이기에 +1 해줌
        //시작 페이지
        int startPage = Math.max(nowPage - 4, 1); // nowPage - 4 의 값이 1보다 작을 경우 1이 출력. 즉 두가지 중에 작은 값으로 출력하게 됨
        //마지막 페이지
        int endPage = Math.min(nowPage + 5, list.getTotalPages()); //nowPage + 5 이 값이 총 페이지 수 보다 클 경우 총 페이지 수가 출력됨

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("name", name);

        System.out.println(list);

        return "/review/reviewList";
    }


    @GetMapping("/view")//localhost:8080/review/view?id=1
    public String review(@RequestParam Long reviewPostId, Model model, Principal principal) throws Exception{

        ReviewPost reviewPost = reviewPostService.reviewPostView(reviewPostId);

        model.addAttribute("review", reviewPost);
        // 글쓴이 아이디
        model.addAttribute("memberId", reviewPost.getMember().getId());

        Member member = memberService.findMemberByLoginId(principal.getName());
        int like = reviewPostService.findLike(reviewPostId, member.getId());

        model.addAttribute("like", like);
        model.addAttribute("memberId", member.getId());

        return "/review/view";
    }

    @GetMapping("/myList")
    public String reviewMyList(Model model, Pageable pageable, @AuthenticationPrincipal User user) {
        Member member = memberService.findMemberByLoginId(user.getUsername());
        Page<ReviewPost> reviewPost = reviewPostService.reviewPostList(pageable);

        Page<ReviewPost> resultPage = PageableExecutionUtils.getPage(
                reviewPost.filter(n -> n.getMember().equals(member))
                        .stream().collect(Collectors.toList()), pageable,
                () -> reviewPost.filter(n -> n.getMember().equals(member)).stream().count());

        int nowPage = resultPage.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, resultPage.getTotalPages());
        model.addAttribute("reviewPost", resultPage);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "review/reviewMyList";
    }

    @ResponseBody
    @PostMapping("like")
    public int like(Long reviewPostId, Long memberId) {
        return reviewPostService.saveLike(reviewPostId, memberId);
    }

    @GetMapping("/modify/{reviewPostId}")
    public String modify(@PathVariable Long reviewPostId, Model model) {
        model.addAttribute("review",reviewPostService.reviewPostView(reviewPostId));
        return "/review/reviewModify";
    }

    @RequestMapping(path = "/update/{reviewPostId}", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String update(@PathVariable("reviewPostId") Long reviewPostId,
                         @ModelAttribute ReviewPostDto reviewPostDto) throws Exception {

        reviewPostService.update(reviewPostId, reviewPostDto);

        return "redirect:/review/list";
    }

    @GetMapping("/delete/{reviewPostId}")
    public String delete(@PathVariable Long reviewPostId) {
        reviewPostService.delete(reviewPostId);
        return "redirect:/review/list";
    }
}