package twogtwoj.whereishere.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

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


    public Member(String memberLoginId, String memberLoginPw, String memberName, LocalDate memberBirthday) {
        this.memberLoginId = memberLoginId;
        this.memberLoginPw = memberLoginPw;
        this.memberName = memberName;
        this.memberBirthday = memberBirthday;
    }
    public Member(){}
}
