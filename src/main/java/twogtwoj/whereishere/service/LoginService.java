package twogtwoj.whereishere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.Member;
import twogtwoj.whereishere.repository.CompanyRepository;
import twogtwoj.whereishere.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    private final CompanyRepository companyRepository;




    // 멤버 로그인 PW 찾기
    public Member loginMember(String loginId, String loginPw) {
        return memberRepository.findByLonginId(loginId)
                .filter(m -> {
                    //m.getMemberLoginPw().equals(loginPw)
                    if ( new BCryptPasswordEncoder().matches(loginPw, m.getMemberLoginPw()) ) {
                        return true;
                    } else {
                        return false;
                    }
                }) // 패스워드 맞는지 확인 // 필터
                .orElse(null); // 패스워드 맞는게 없으면 null
    }

    // 기업 로그인 PW 찾기
    public Company loginCompany(String loginId, String loginPw){
        return companyRepository.findByLonginId(loginId)
                .filter(company -> {
                    if (new BCryptPasswordEncoder().matches(loginPw, company.getCompanyLoginPw())){
                        return true;
                    }else {
                        return false;
                    }
                })
                .orElse(null);
    }
}
