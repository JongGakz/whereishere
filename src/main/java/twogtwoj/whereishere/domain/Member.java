package twogtwoj.whereishere.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true) // 중복 불가
    private String LoginId;

    private String LoginPw;

    private String name;

    private LocalDate memberBirthday;


    public Member(String memberLoginId, String memberLoginPw, String memberName, LocalDate memberBirthday) {
        this.LoginId = memberLoginId;
        this.LoginPw = memberLoginPw;
        this.name = memberName;
        this.memberBirthday = memberBirthday;
    }
    public Member(){}
}
