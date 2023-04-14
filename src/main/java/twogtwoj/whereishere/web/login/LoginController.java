package twogtwoj.whereishere.web.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.Member;
import twogtwoj.whereishere.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/")
    public String loginGet(@ModelAttribute("loginForm") LoginForm form){
        return "/index";
    }

    @PostMapping("/")
    public String memberLogin(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, // bindingResult 검증 결과 객체 전달
                              @RequestParam String loginCategory, HttpServletRequest request) { // HttpServletRequest request : 로그인한 사용자의 정보를 세션에 저장하는 것

        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }

        if(loginCategory.equals("Member")) {
            Member loginMember = loginService.loginMember(form.getLoginId(), form.getLoginPw()); // 회원 비밀번호 찾기
            if(loginMember == null){
                return "redirect:/";
            }
            //로그인 성공 처리
            //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
            HttpSession session = request.getSession();
            //세션에 로그인 회원 정보 보관
            session.setAttribute(SessionConst.LOGIN_Member, loginMember);

        } else if (loginCategory.equals("Company")) {
           Company loginCompany =  loginService.loginCompany(form.getLoginId(), form.getLoginPw());

            if(loginCompany == null){
                return "redirect:/";
            }

            HttpSession session = request.getSession();

            session.setAttribute(SessionConst.LOGIN_Company, loginCompany);

        } else {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "redirect:/";
        }
        return "redirect:/home/home";
    }

    @GetMapping("/home/home")
    public String homeGO(){
        return "home/home";
    }
}
