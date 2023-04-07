package twogtwoj.whereishere.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
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

    private LocalDate commentWriteDate;
}
