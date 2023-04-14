package twogtwoj.whereishere.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.ReviewPost;
import twogtwoj.whereishere.file.FileStore;
import twogtwoj.whereishere.service.CompanyService;
import twogtwoj.whereishere.service.ReviewPostService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewPostController {
    /*
    1. 후기 게시판 메인
    2. 내가 쓴 글 보기
    3. 글쓰기
    4. 게시물 상세보기
     */
    private final ReviewPostService reviewPostService;

    private final CompanyService companyService;

    private final FileStore fileStore;
    @GetMapping
    public String reviews(Model model) {
        List<ReviewPost> reviews = reviewPostService.findAllReviewPost();
        model.addAttribute("reviews", reviews);
        return "/review/reviews";
    }

    // 리뷰포스트 게시글 작성 클릭시 게시글 작성 페이지로 이동
    // ** html 에서 스크립트로 자동완성 기능 구현 필요
    @GetMapping("/reviews/post")
    public String reviewPostForm(Model model) {
        List<Company> companyList = companyService.findAll();
        model.addAttribute("companyList",companyList);
        return "/review/reviewPost";
    }


    // 리뷰 등록했을 때, ReviewPost에 객체로 저장하는 메서드
    @PostMapping("/reviews/post")
    public String saveReviewPost(@RequestParam String companyName, @RequestParam String reviewPostTitle,
                                 @RequestParam String reviewPostContent, @RequestParam MultipartFile reviewPostImg1,
                                 @RequestParam MultipartFile reviewPostImg2) throws IOException {

        Company findCompany = companyService.findCompanyByCompanyName(companyName);
        String reviewPostImg1Name = UUID.randomUUID() + ".png";
        String reviewPostImg2Name = UUID.randomUUID() + ".png";
        reviewPostImg1.transferTo(new File(fileStore.getFullPath(reviewPostImg1Name)));
        reviewPostImg2.transferTo(new File(fileStore.getFullPath(reviewPostImg2Name)));
        reviewPostService.save(new ReviewPost(findCompany, reviewPostTitle,reviewPostContent, reviewPostImg1Name ,
                reviewPostImg2Name , LocalDate.now()));

        return "/review/reviews";
    }


    // 리뷰 ID를 받아서 해당 게시글을 보여주는 메서드
    @PostMapping("/reviews/{reviewPostId}")
    public String showReviewPost(@PathVariable Long reviewPostId ,Model model) {
        ReviewPost reviewPost = reviewPostService.findByReviewId(reviewPostId);
        model.addAttribute("reviewPost", reviewPost);

        return "/review/reviewPostView";
    }


}
