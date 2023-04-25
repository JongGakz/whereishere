package twogtwoj.whereishere.web;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import twogtwoj.whereishere.domain.ReviewPost;
import twogtwoj.whereishere.dto.ReviewPostDto;
import twogtwoj.whereishere.file.FileStore;
import twogtwoj.whereishere.repository.ReviewPostRepository;
import twogtwoj.whereishere.service.ReviewPostService;

import java.net.MalformedURLException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewPostController {

    private final ReviewPostService reviewPostService;

    private  final ReviewPostRepository reviewPostRepository;

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
    //@PostMapping("/postPro")//안쓰는 경우가 많지만 개발에서 필요한 경우도 있음 알아두는 것이 좋음

    @RequestMapping(path = "/postPro", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String reviewPostPro(@ModelAttribute ReviewPostDto reviewPostDto, Model model) throws Exception{
        reviewPostService.post(reviewPostDto);

        model.addAttribute("message", "작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/review/list");

        return "/review/message";
    }

    @SneakyThrows
    @GetMapping("/list")
    public String reviewList(@PageableDefault(size = 10, sort = "reviewPostId", direction = Sort.Direction.DESC) Pageable pageable,
                                 @RequestParam(name = "searchKeyword", required = false) String searchKeyword,
                                 @RequestParam(name = "companyName", required = false) String companyName,
                                 Model model) {

        Page<ReviewPost> list = null;

        if (searchKeyword == null && companyName == null) {
            list = reviewPostService.reviewPostList(pageable);
        } else if (searchKeyword != null && companyName != null) {
            list = reviewPostService.reviewPostSearchList(searchKeyword, companyName, pageable);
        } else if (searchKeyword != null) {
            list = reviewPostService.reviewPostSearchList(searchKeyword, null, pageable);
        } else if (companyName != null) {
            list = reviewPostService.reviewPostSearchList(null, companyName, pageable);
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
        model.addAttribute("companyName", companyName);

        System.out.println(list);

        return "/review/reviewList";
    }


    @GetMapping("/view")//localhost:8080/review/view?id=1
    public String review(@RequestParam Long reviewPostId, Model model) throws Exception{

        ReviewPost reviewPost = reviewPostService.reviewPostView(reviewPostId);

        model.addAttribute("review", reviewPost);

        return "/review/view";
    }

    @GetMapping("/modify/{reviewPostId}")
    public String modify(@PathVariable Long reviewPostId, Model model) {
        model.addAttribute("review",reviewPostService.reviewPostView(reviewPostId));
        return "/review/reviewModify";
    }

    @RequestMapping(path = "/update/{reviewPostId}", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String update(@PathVariable("reviewPostId") Long reviewPostId,
                         @ModelAttribute ReviewPostDto reviewPostDto,
                         Model model) throws Exception {

        reviewPostService.update(reviewPostId, reviewPostDto);

        return "redirect:/review/list";
    }

    @GetMapping("/delete/{reviewPostId}")
    public String delete(@PathVariable Long reviewPostId) {
        reviewPostService.delete(reviewPostId);
        return "redirect:/review/list";
    }
}
