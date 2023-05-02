package twogtwoj.whereishere.repository;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import twogtwoj.whereishere.domain.ReviewPost;

@Repository
public interface ReviewPostRepository extends JpaRepository<ReviewPost, Long>, JpaSpecificationExecutor<ReviewPost> {

    Page<ReviewPost> findByReviewPostTitleContainingAndNameContaining(String reviewPostTitle, String name, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update ReviewPost r set r.likeCount = r.likeCount+1 where r.reviewPostId = :reviewPostId")
    int plusReviewLike(@Param("reviewPostId") Long reviewPostId);

    @Transactional
    @Modifying
    @Query(value = "update ReviewPost r set r.likeCount = r.likeCount-1 where r.reviewPostId = :reviewPostId")
    int minusReviewLike(@Param("reviewPostId") Long reviewPostId);
}
