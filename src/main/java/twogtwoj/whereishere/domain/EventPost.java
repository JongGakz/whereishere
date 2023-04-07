package twogtwoj.whereishere.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class EventPost { // 이벤트 게시판

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventPostId;


    @ManyToOne
    @JoinColumn(name ="COMPANY_ID")
    private Company company;

    private String eventPostTitle;

    private String eventPostContent;

    private String eventPostImg;

    private LocalDate eventPostWriteDate;
}
