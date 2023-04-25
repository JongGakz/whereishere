package twogtwoj.whereishere.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import twogtwoj.whereishere.domain.Member;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Repository
public class MemberRepository {
    private final EntityManager em;

    public Member save(Member member) {
        if(member.getMemberId() == null) {
            em.persist(member);
        } else {
            em.merge(member);
        }
        return member;
    }

    public Optional<Member> findByMemberId(Long memberId) {
        Member member = em.find(Member.class, memberId);
        return Optional.ofNullable(member);
    }

}
