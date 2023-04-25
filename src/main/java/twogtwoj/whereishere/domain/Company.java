package twogtwoj.whereishere.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    //@Range(min = 5, max = 15)
    private String companyLoginId;

   // @Range(min = 8, max = 20)
    private String companyLoginPw;

    private Long companyBusinessId; // 사업자 번호

   // @Range(min = 2, max = 100)
    private String companyName;

    private String companyImg;

    @Column(length = 10000)
    private String companyIntroduction;

    private String companyCategory;

    private String companyAddress;

    public Company(String companyLoginId, String companyLoginPw, Long companyBusinessId, String companyName, String companyImg, String companyIntroduction, String companyCategory, String companyAddress) {
        this.companyLoginId = companyLoginId;
        this.companyLoginPw = companyLoginPw;
        this.companyBusinessId = companyBusinessId;
        this.companyName = companyName;
        this.companyImg = companyImg;
        this.companyIntroduction = companyIntroduction;
        this.companyCategory = companyCategory;
        this.companyAddress = companyAddress;
    }
    public Company() {

    }

}
