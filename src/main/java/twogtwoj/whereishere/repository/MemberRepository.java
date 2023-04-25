package twogtwoj.whereishere.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.Member;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Repository
public class MemberRepository {

    private final PasswordEncoder passwordEncoder;
    private final EntityManager em;

    public Member save(Member member) {
        member.setLoginPw(passwordEncoder.encode(member.getLoginPw()));
        em.persist(member);
        return member;
    }

    public Optional<Member> findMemberByMemberId(Long memberId) {
        Member member = em.find(Member.class, memberId);
        return Optional.ofNullable(member);
    }
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public Member findMemberByLoginId(String loginId) {
        return (Member)(findAll().stream().filter(n -> n.getLoginId().equals(loginId)).toArray()[0]);
    }

}
