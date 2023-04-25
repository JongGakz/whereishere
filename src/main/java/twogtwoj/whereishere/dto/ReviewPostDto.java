package twogtwoj.whereishere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPostDto {

    private String companyName;
    private String reviewPostTitle;
    private String reviewPostContent;
    private MultipartFile file1;
    private MultipartFile file2;

}


