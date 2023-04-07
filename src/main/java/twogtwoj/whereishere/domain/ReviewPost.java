package twogtwoj.whereishere.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class ReviewPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewPostId;


    @ManyToOne
    @JoinColumn(name ="MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name ="COMPANY_ID")
    private Company company;

    private String reviewPostTitle;

    private String reviewPostContent;

    private String reviewPostImg;

    private LocalDate reviewPostDate;
}
