package twogtwoj.whereishere.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Comment { // 업체 한줄평

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;


    @ManyToOne
    @JoinColumn(name ="MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name ="COMPANY_ID")
    private Company company;

    private String commentContent;

    private LocalDateTime commentWriteDate;

    public Comment(Member member, Company company, String commentContent, LocalDateTime commentWriteDate) {
        this.member = member;
        this.company = company;
        this.commentContent = commentContent;
        this.commentWriteDate = commentWriteDate;
    }
}
