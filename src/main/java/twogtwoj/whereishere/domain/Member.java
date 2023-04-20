package twogtwoj.whereishere.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import twogtwoj.whereishere.web.Login.Role;


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

    @Enumerated(EnumType.STRING)
    private Role memberRole;


    public Member(String memberLoginId, String memberLoginPw, String memberName, LocalDate memberBirthday, Role memberRole) {
        this.memberLoginId = memberLoginId;
        this.memberLoginPw = memberLoginPw;
        this.memberName = memberName;
        this.memberBirthday = memberBirthday;
        this.memberRole = memberRole;
    }


}
