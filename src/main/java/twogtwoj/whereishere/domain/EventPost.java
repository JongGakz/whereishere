package twogtwoj.whereishere.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import twogtwoj.whereishere.repository.CommentRepository;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private String eventPostImg1;

    private String eventPostImg2;

    private LocalDate eventPostWriteDate;

    public EventPost(Company company, String eventPostTitle, String eventPostContent, String eventPostImg1, String eventPostImg2, LocalDate eventPostWriteDate) {
        this.company = company;
        this.eventPostTitle = eventPostTitle;
        this.eventPostContent = eventPostContent;
        this.eventPostImg1 = eventPostImg1;
        this.eventPostImg2 = eventPostImg2;
        this.eventPostWriteDate = eventPostWriteDate;
    }
    public EventPost(){}
}
