package twogtwoj.whereishere.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;


    //@Range(min = 5, max = 15)
    private String memberLoginId;

    //@Range(min = 8, max = 20)
    private String memberLoginPw;

    //@Range(min = 2, max = 100)
    private String memberName;

    private LocalDate memberBirthday;
    @OneToMany(mappedBy = "member")
    private List<ReviewPost> reviewPostList = new ArrayList<>();

    public Member(String memberLoginId, String memberLoginPw, String memberName, LocalDate memberBirthday) {
        this.memberLoginId = memberLoginId;
        this.memberLoginPw = memberLoginPw;
        this.memberName = memberName;
        this.memberBirthday = memberBirthday;
    }
    public Member(){}

}
