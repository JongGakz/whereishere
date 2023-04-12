package twogtwoj.whereishere.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewLikeId;

    @ManyToOne
    @JoinColumn(name ="REVIEW_POST_ID")
    private ReviewPost reviewPost;

    @ManyToOne
    @JoinColumn(name ="MEMBER_ID")
    private Member member;


    private int reviewLikePoint; // 좋아요 갯수

    public static ReviewLike like(Member member, ReviewPost reviewPost) {
        ReviewLike reviewLike = new ReviewLike();
        reviewLike.setMember(member);
        reviewLike.setReviewPost(reviewPost);

        return reviewLike;
    }

}
