package twogtwoj.whereishere.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    //@Column(unique = true) // 중복 불가
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

}
