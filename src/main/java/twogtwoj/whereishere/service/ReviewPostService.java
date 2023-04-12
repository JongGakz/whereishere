package twogtwoj.whereishere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import twogtwoj.whereishere.domain.ReviewPost;
import twogtwoj.whereishere.repository.ReviewPostRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewPostService {
    /*
    게시글 등록
    수정
    삭제
    리스트
    게시글 찾기(
     */
    private final ReviewPostRepository reviewPostRepository;

    public ReviewPost save(ReviewPost reviewPost) {
        return reviewPostRepository.save(reviewPost);
    }

    public ReviewPost findByReviewId(Long reviewPostId) {
        return reviewPostRepository.findByReviewPostId(reviewPostId);
    }

    ReviewPost findByReviewPostTitle(String reviewPostTitle) {
        return reviewPostRepository.findByReviewPostTitle(reviewPostTitle);
    }
    public List<ReviewPost> findAllReviewPost() {
        return reviewPostRepository.findAllReviewPost();
    }

    public void update(Long reviewPostId, ReviewPost updateReviewPost) {
        reviewPostRepository.update(reviewPostId, updateReviewPost);
    }

    public void delete(Long reviewPostId) {
        reviewPostRepository.delete(reviewPostId);
    }

    

}
