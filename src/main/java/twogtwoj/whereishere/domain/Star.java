package twogtwoj.whereishere.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Star {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long starId;


    @ManyToOne
    @JoinColumn(name ="COMPANY_ID")
    private Company company;

    @ManyToOne
    @JoinColumn(name ="MEMBER_ID")
    private Member member;

    private int starPoint;
}
