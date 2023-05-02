package twogtwoj.whereishere.web;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.Member;
import twogtwoj.whereishere.file.FileStore;
import twogtwoj.whereishere.service.CompanyServiceImpl;
import twogtwoj.whereishere.service.MemberService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class LogInController {

    private final MemberService memberService;
    private final FileStore fileStore;

    private final CompanyServiceImpl companyServiceImpl;

    @GetMapping("/login")
    public String viewLoginPage() {
        return "/login/login";
    }

    @GetMapping("/login/signup")
    public String viewSignUpPage() {
        return "/login/signup";
    }

    @GetMapping("/login/signup/member")
    public String viewSingUpMemberPage(Model model) {
        List<Member> memberList = memberService.findAll();
        model.addAttribute("memberList",memberList);
        return "/login/signup/member";
    }


    @PostMapping("/login/signup/member")
    public String signUpMember(String memberLoginId, String memberLoginPw, String memberName,
                               Integer year, Integer month, Integer day, RedirectAttributes redirectAttributes) {

        // 아이디 중복확인 버튼 만들기 ()
        memberService.save(new Member(memberLoginId, memberLoginPw, memberName, LocalDate.of(year, month, day)));

        redirectAttributes.addAttribute("status", true);
        return "redirect:/login";
    }

    @GetMapping("/login/signup/company")
    public String viewSingUpCompanyPage(Model model) {
        List<Company> companyList = companyServiceImpl.findAll();
        model.addAttribute("companyList",companyList);
        return "/login/signup/company";
    }


    @PostMapping("/login/signup/company")
    public String signUpCompany(String companyLoginId, String companyLoginPw, Long companyBusinessId,
                                String companyName, String companyCategory, String companyAddress, MultipartFile companyImg,
                                String companyIntroduction, RedirectAttributes redirectAttributes) throws IOException {

        String companyImgName = UUID.randomUUID() + ".png";
        companyImg.transferTo(new File(fileStore.getFullPath(companyImgName)));
        companyServiceImpl.save(new Company(companyLoginId, companyLoginPw, companyBusinessId, companyName, companyImgName, companyIntroduction, companyCategory, companyAddress));

        redirectAttributes.addAttribute("status", true);
        return "redirect:/login";
    }
}
