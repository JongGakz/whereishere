package twogtwoj.whereishere.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.Member;
import twogtwoj.whereishere.repository.CompanyRepositoryImpl;
import twogtwoj.whereishere.repository.MemberRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final CompanyRepositoryImpl companyRepositoryImpl;



    @Autowired @Lazy
    public UserDetailsServiceImpl(MemberRepository memberRepository, CompanyRepositoryImpl companyRepositoryImpl) {
        this.memberRepository = memberRepository;
        this.companyRepositoryImpl = companyRepositoryImpl;

    }


    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        // userType 파라미터를 이용하여 Member 또는 Company 도메인을 조회합니다.
        String userType = getUserTypeFromRequest();
        Object user = null;
        if (userType.equals("member")) {
            user = memberRepository.findMemberByLoginId(loginId);
        } else if (userType.equals("company")) {
            user = companyRepositoryImpl.findCompanyByLoginId(loginId);
        }

        // 조회된 도메인이 없을 경우 예외를 발생시킵니다.
        if (user == null) {
            throw new UsernameNotFoundException("Invalid login id");
        }

        // 인증 정보를 설정합니다.
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (userType.equals("member")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
            Member member = (Member) user;
            return new User(member.getLoginId(), member.getLoginPw(), authorities);
        } else if (userType.equals("company")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_COMPANY"));
            Company company = (Company) user;
            return new User(company.getLoginId(), company.getLoginPw(), authorities);
        } else {
            throw new UsernameNotFoundException("Invalid user type");
        }
    }

    private String getUserTypeFromRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String userTypeStr = request.getParameter("userType");
        if (userTypeStr == null) {
            return null;
        } else if (userTypeStr.equals("member")) {
            return "member";
        } else if (userTypeStr.equals("company")) {
            return "company";
        } else {
            return null;
        }
    }
}