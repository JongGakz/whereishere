package twogtwoj.whereishere.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import twogtwoj.whereishere.domain.Member;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Repository
public class MemberRepository {

    private final EntityManager em;

    public Member save(Member member) {
        member.setMemberLoginPw(new BCryptPasswordEncoder().encode(member.getMemberLoginPw()));
        em.persist(member);
        return member;
    }

    public Member findOne(Long memberId) {
        return em.find(Member.class, memberId);
    }



    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.memberName = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    // Optional : jpa null 인셉션 막기용
    // 멤버 테이블에 있는 로그인id 찾기 // 값이 있을 경우에만 true
    public Optional<Member> findByLoginId(String loginId){ // 클라에게 받은 로그인 id(html에서 전달 받은)
        return findAll().stream()
                .filter(member -> member.getMemberLoginId()
                        .equals(loginId)).findFirst();
    }
}
