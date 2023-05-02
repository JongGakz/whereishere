package twogtwoj.whereishere.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPostDto {

    private Long reviewPostId;
    private String name;//기업명
    private String reviewPostTitle;
    private String reviewPostContent;
    private MultipartFile file1;
    private MultipartFile file2;

}


