package twogtwoj.whereishere.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import twogtwoj.whereishere.domain.ReviewPost;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
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

    Optional<ReviewPost> findByReviewPostId(Long reviewPostId) {};
}
