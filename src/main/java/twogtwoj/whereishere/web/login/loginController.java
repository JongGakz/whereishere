package twogtwoj.whereishere.web.login;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import twogtwoj.whereishere.domain.Member;
import twogtwoj.whereishere.repository.CompanyRepository;
import twogtwoj.whereishere.repository.MemberRepository;
import twogtwoj.whereishere.service.CompanyService;
import twogtwoj.whereishere.service.MemberService;

@Slf4j
@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class loginController {

    private final MemberService memberService;
    private final CompanyService companyService;

    // 로그인

    // 회원 가입 메인 페이지(개인/기업 구분 페이지)
    @GetMapping("/SignUp")
    public String signUpMain(){
        return "/login/SignUp";
    }


    // 회원 가입(개인)
    @GetMapping("/SignUpMember")
    public String saveFormMember(){
        return "/login/SignUpMember";
    }

    // 회원 가입(개인)
    @PostMapping("/SignUpMember")
    public String saveMember(@ModelAttribute Member member){
        memberService.save(member);
        return "redirect:/"; // 로그인 이후 페이지
    }


    // 회원 가입(기업)
    @GetMapping("/SignUpCompany")
    public String saveFormCompany(){
        return "/login/SignUpCompany";
    }

}
