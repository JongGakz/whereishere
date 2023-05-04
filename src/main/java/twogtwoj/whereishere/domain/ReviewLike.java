package twogtwoj.whereishere.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
public class ReviewLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewLikeId;

    @ManyToOne
    @JoinColumn(name = "REVIEW_POST_ID")
    private ReviewPost reviewPost;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public static ReviewLike toReviewLike(Member member, ReviewPost reviewPost){
        ReviewLike reviewLike = new ReviewLike();
        reviewLike.setMember(member);
        reviewLike.setReviewPost(reviewPost);

        return reviewLike;
    }

}
