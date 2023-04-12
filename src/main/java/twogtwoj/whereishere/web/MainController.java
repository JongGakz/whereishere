package twogtwoj.whereishere.web;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.service.CompanyService;
import twogtwoj.whereishere.service.MemberService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;

    private final CompanyService companyService;

    @GetMapping("/home")
    public String enterHome() {return "/home/home";}

    @PostMapping("/home")
    public String searchPlace(@RequestParam String search, Model model) {
        System.out.println(search);
        List<Company> companies = companyService.findAll();

        List<Company> companyList = companies.stream().filter(n -> n.getCompanyIntroduction().contains(search)).collect(Collectors.toUnmodifiableList());

        model.addAttribute("companyList",companyList);

        return "/home/home";
    }
}
