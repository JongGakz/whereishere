package twogtwoj.whereishere.domain;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Getter
@Setter
public class ReviewPost {//후기 게시판

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewPostId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    private String name;

    private String writer;

    private String reviewPostTitle;


    @Column(columnDefinition = "varchar(1000)")
    private String reviewPostContent;

    private String reviewPostImg1;

    private String reviewPostImg2;

    private int likeCount;//좋아요

    private LocalDate reviewPostDate;

    @PrePersist //DB 에 insert 되기 직전에 실행됨
    public void reviewPostDate() {
        this.reviewPostDate = LocalDate.now();
    }

    public ReviewPost() {

    }
    public ReviewPost(Member member, Company company, String name, String writer, String reviewPostTitle, String reviewPostContent, String reviewPostImg1, String reviewPostImg2, int likeCount, LocalDate reviewPostDate) {
        this.member = member;
        this.company = company;
        this.name = name;
        this.writer = writer;
        this.reviewPostTitle = reviewPostTitle;
        this.reviewPostContent = reviewPostContent;
        this.reviewPostImg1 = reviewPostImg1;
        this.reviewPostImg2 = reviewPostImg2;
        this.likeCount = likeCount;
        this.reviewPostDate = reviewPostDate;
    }
}
