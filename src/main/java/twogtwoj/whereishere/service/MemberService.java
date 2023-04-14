package twogtwoj.whereishere.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import twogtwoj.whereishere.domain.Member;
import twogtwoj.whereishere.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 멤버 정보 db 저장
    public Member save(Member member){
        return memberRepository.save(member);
    }

}
