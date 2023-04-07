package twogtwoj.whereishere.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
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

    private int reviewLikePoint; // 개추
}
