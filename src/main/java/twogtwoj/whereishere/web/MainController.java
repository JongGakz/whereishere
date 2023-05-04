package twogtwoj.whereishere.web;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import twogtwoj.whereishere.domain.Comment;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.Member;
import twogtwoj.whereishere.domain.Star;
import twogtwoj.whereishere.service.CommentService;
import twogtwoj.whereishere.service.CompanyServiceImpl;
import twogtwoj.whereishere.service.MemberService;
import twogtwoj.whereishere.service.StarService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;

    private final CompanyServiceImpl companyServiceImpl;

    private final StarService starService;

    private final CommentService commentService;



    @GetMapping("/main")
    public String enterMain(Model model) {
        List<Company> companyList = companyServiceImpl.findAll();
        model.addAttribute("companyList",companyList);
        return "/main";
    }



    @GetMapping("/home")
    public String enterHome(Model model) {
        List<Company> companyList = new ArrayList<>();
        model.addAttribute("companyList", companyList);
        return "/home/home";
    }

    @PostMapping("/home")
    public String searchPlace(@RequestParam String search, Model model) {

        if (search.trim().equals("")) {
            List<Company> companyList = new ArrayList<>();
            model.addAttribute("companyList", companyList);
            return "/home/home";
        } else {

            List<Company> companies = companyServiceImpl.findAll();

            List<Company> companyList = companies.stream().filter(n -> n.getCompanyIntroduction().contains(search) || n.getName().contains(search)).collect(Collectors.toUnmodifiableList());

            model.addAttribute("companyList", companyList);

            return "/home/home";
        }
    }

    // 검색한 업체 정보 바로가기
    @GetMapping("/companies/{companyId}")
    public String informationCompany(@PathVariable Long companyId, Model model, @AuthenticationPrincipal User user) {

        Company company = companyServiceImpl.findCompanyByCompanyId(companyId);

        // 업체에 등록된 별점객체들 찾기 (별점이 등록된 것이 없으면 0 반환)
        Double starsPoint = starService.findStarsPointByCompany(company);

        if (starsPoint.isNaN()) {
            starsPoint = 0.0;
        }
        String starsPointToString = String.format("%.1f", starsPoint);
        // 업체에 등록된 코멘트 리스트 찾기
        List<Comment> commentList = commentService.findCommentListByCompany(company);

        List<Star> allStars = starService.findAll();
        Member member = memberService.findMemberByLoginId(user.getUsername());

        model.addAttribute("member",member);
        model.addAttribute("allStars",allStars);
        model.addAttribute("company", company);
        model.addAttribute("starsPoint", starsPointToString);
        model.addAttribute("commentList", commentList);

        return "/home/infocompany";
    }


//    ***

    // 별점 매기기 (파라미터가 false로 왔을때, 별점등록이 실패했다, true일때 성공했다라는 알람이 울릴 수있게 만들기)
    @PostMapping("/companies/{companyId}/star")
    public String saveStarPoint(@PathVariable Long companyId, @RequestParam Long starPoint, RedirectAttributes redirectAttributes, @AuthenticationPrincipal User user) {
        Member member = memberService.findMemberByLoginId(user.getUsername());
        // 해당 컴퍼니
        Company company = companyServiceImpl.findCompanyByCompanyId(companyId);

        // 현재, 이맴버가 이 컴페니에 별점을 남겼더라면, 적용이 되지않고 리턴할 수 있게 만들기
        List<Star> allStars = starService.findAll();

        for (int i = 0; i < allStars.size(); i++) {
            if (allStars.get(i).getMember().equals(member) && allStars.get(i).getCompany().equals(company)) {
                // 저장을 실패하고 되돌아가기
                redirectAttributes.addAttribute("companyId", companyId);
                redirectAttributes.addAttribute("status", false);
                return "redirect:/companies/{companyId}";

            }
        }

        //starPoint 와 함께 새로운 stars 객체 저장
        starService.save(new Star(member, company, starPoint.doubleValue()));

        // 저장을 성공적으로 하고 되돌아가기
        redirectAttributes.addAttribute("companyId", companyId);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/companies/{companyId}";
    }


    // 코멘트 입력시 추가하고, 다시 검색한 업체 정보
    @PostMapping("/companies/{companyId}/comment")
    public String saveComment(@PathVariable Long companyId, @RequestParam String commentContent, RedirectAttributes redirectAttributes, @AuthenticationPrincipal User user) {

        if (!commentContent.trim().equals("")) {
            Member member = memberService.findMemberByLoginId(user.getUsername());

            // 회사 객체를 찾아, commentContent 와 함께, 새로운 코멘트객체 저장
            Company company = companyServiceImpl.findCompanyByCompanyId(companyId);
            commentService.save(new Comment(member, company, commentContent, LocalDateTime.now()));

            // 되돌아가기
            redirectAttributes.addAttribute("companyId", companyId);
            redirectAttributes.addAttribute("status", true);
            return "redirect:/companies/{companyId}";
        } else {
            redirectAttributes.addAttribute("status", false);
            return "redirect:/companies/{companyId}";
        }
    }


    // 업체 정보 페이지로 바로가기

    @GetMapping("/serachcompanylist")
    public String enterSearchCompanyList(Model model) {
        List<Company> companyList = companyServiceImpl.findAll();
        model.addAttribute("companyList", companyList);

        return "/home/searchcompanylist";
    }
}

