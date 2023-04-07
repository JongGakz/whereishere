package twogtwoj.whereishere.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import twogtwoj.whereishere.domain.Member;
import twogtwoj.whereishere.repository.MemberRepository;



@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(Member member){
        return memberRepository.save(member);
    }
}
