package twogtwoj.whereishere.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import twogtwoj.whereishere.domain.Member;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Repository
public class MemberRepository {

    private final EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }
}
