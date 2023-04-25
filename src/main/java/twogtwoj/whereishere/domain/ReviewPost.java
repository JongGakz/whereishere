package twogtwoj.whereishere.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    private String companyName;

    private String writer;

    private String reviewPostTitle;

     private String reviewPostContent;

    private String reviewPostImg1;

    private String reviewPostImg2;

    private int liked;//좋아요

    private LocalDate reviewPostDate;

    @PrePersist //DB 에 insert 되기 직전에 실행됨
    public void reviewPostDate() {
        this.reviewPostDate = LocalDate.now();
    }

    public ReviewPost() {

    }

}
