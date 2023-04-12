package twogtwoj.whereishere.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import twogtwoj.whereishere.domain.ReviewPost;
import twogtwoj.whereishere.repository.ReviewPostRepository;
import twogtwoj.whereishere.service.ReviewPostService;

import java.util.List;

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

    @GetMapping
    public String reviews(Model model) {
        List<ReviewPost> reviews = reviewPostService.findAllReviewPost();
        model.addAttribute("reviews", reviews);
        return "/review/reviews";
    }
}
