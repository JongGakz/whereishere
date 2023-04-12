package twogtwoj.whereishere.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import twogtwoj.whereishere.domain.ReviewPost;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class ReviewPostRepository {

    private final EntityManager em;

    public ReviewPost save(ReviewPost reviewPost) {
        if (reviewPost.getReviewPostId() == null) {
            em.persist(reviewPost);
        } else {
            em.merge(reviewPost);
        }
        return reviewPost;
    }

    public Optional<ReviewPost> findByReviewPostId(Long reviewPostId) {
        ReviewPost reviewPost = em.find(ReviewPost.class, reviewPostId);
        return Optional.ofNullable(reviewPost);
    }

    public ReviewPost findByReviewPostTitle(String reviewPostTitle) {
        return null;
    }

    public List<ReviewPost> findAllReviewPost() {
        return em.createQuery("select r from ReviewPost r", ReviewPost.class).getResultList();
    }

    public void update(Long reviewPostId, ReviewPost updateReviewPost) {
        ReviewPost reviewPost = findByReviewPostId(reviewPostId).get();
        reviewPost.setReviewPostTitle(updateReviewPost.getReviewPostTitle());
        reviewPost.setReviewPostContent(updateReviewPost.getReviewPostContent());
        reviewPost.setReviewPostImg1(updateReviewPost.getReviewPostImg1());
        reviewPost.setReviewPostImg2(updateReviewPost.getReviewPostImg2());
        reviewPost.setCompany(updateReviewPost.getCompany());
    }

    public void delete(Long reviewPostId) {
        em.remove(findByReviewPostId(reviewPostId).get());
    }
}

