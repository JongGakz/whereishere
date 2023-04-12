package twogtwoj.whereishere.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String memberLoginId;

    private String memberLoginPw;

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
