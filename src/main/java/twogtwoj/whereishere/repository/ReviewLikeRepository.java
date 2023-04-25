package twogtwoj.whereishere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import twogtwoj.whereishere.domain.Member;
import twogtwoj.whereishere.domain.ReviewLike;
import twogtwoj.whereishere.domain.ReviewPost;

@Repository
public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {

    ReviewLike findByReviewPostAndMember(ReviewPost reviewPost, Member member);
    void  deleteByReviewPostAndMember(ReviewPost reviewPost, Member memberId);
   // ReviewLike findByReviewPostAndMember(ReviewPost reviewPost, Member member); //ReviewPost 정보와 Member 의 정보를 바탕으로 ReviewLike 의 객체를 불러옴

}
