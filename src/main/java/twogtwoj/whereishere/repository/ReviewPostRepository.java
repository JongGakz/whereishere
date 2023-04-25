package twogtwoj.whereishere.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import twogtwoj.whereishere.domain.ReviewPost;

@Repository
public interface ReviewPostRepository extends JpaRepository<ReviewPost, Long>, JpaSpecificationExecutor<ReviewPost> {

    Page<ReviewPost> findByReviewPostTitleContainingAndNameContaining(String reviewPostTitle, String name, Pageable pageable);

    @Modifying
    @Query(value = "update ReviewPost r set r.liked = r.liked+1 where r.reviewPostId = :reviewPostId")
    int plusReviewLike(@Param("reviewPostId") Long reviewPostId);

    @Modifying
    @Query(value = "update ReviewPost r set r.liked = r.liked-1 where r.reviewPostId = :reviewPostId")
    int minusReviewLike(@Param("reviewPostId") Long reviewPostId);
}
