package twogtwoj.whereishere.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import twogtwoj.whereishere.domain.Member;
import twogtwoj.whereishere.repository.MemberRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(Member member){
        return memberRepository.save(member);
    }

    public Optional<Member> findMemberByMemberId(Long memberId) {
        return memberRepository.findMemberByMemberId(memberId);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findMemberByLoginId(String loginId) {
        return memberRepository.findMemberByLoginId(loginId);
    }
}
