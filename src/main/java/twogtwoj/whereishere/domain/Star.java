package twogtwoj.whereishere.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Star {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long starId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    private Double starPoint;

    public Star(Member member, Company company, Double starPoint) {
        this.member = member;
        this.company = company;
        this.starPoint = starPoint;
    }
}
