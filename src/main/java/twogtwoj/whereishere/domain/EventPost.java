package twogtwoj.whereishere.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Getter @Setter
public class EventPost { // 이벤트 게시판

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventPostId;
    @ManyToOne
    @JoinColumn(name ="COMPANY_ID")
    private Company company;

    private String eventPostTitle;

    private String eventPostContent;

    private String eventPostImgs;

    private LocalDate eventPostWriteDate;

    public EventPost(Company company, String eventPostTitle, String eventPostContent, String eventPostImgs, LocalDate eventPostWriteDate) {
        this.company = company;
        this.eventPostTitle = eventPostTitle;
        this.eventPostContent = eventPostContent;
        this.eventPostImgs = eventPostImgs;
        this.eventPostWriteDate = eventPostWriteDate;
    }
    public EventPost(){}
}
