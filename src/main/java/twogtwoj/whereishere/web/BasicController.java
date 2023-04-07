package twogtwoj.whereishere.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import twogtwoj.whereishere.domain.Category;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.Member;
import twogtwoj.whereishere.service.CompanyService;
import twogtwoj.whereishere.service.MemberService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class BasicController {

    private final MemberService memberService;

    private final CompanyService companyService;


    @GetMapping("/home")
    public String enterHome() {return "/home/home";}

    @PostConstruct
    public void initMemberAndCompany() {
        memberService.save(new Member("Jonggak","123123","임종각",LocalDate.of(1993,6,16)));

        memberService.save(new Member("afternoon","321321","최정오",LocalDate.of(1997,4,15)));

        memberService.save(new Member("Gagyeong","154689","박가경",LocalDate.of(1988,12,7)));

        memberService.save(new Member("Gzero","181818","김지영",LocalDate.of(1996,7,6)));


        companyService.save(new Company("kitri","3213212",1838100871L,
                "키트리주식회사","이미지.jpg","세상에서 제일 맛있는 김치찌개병원", Category.HOSPITAL.getDescription()
        ,"경기도 부천시 소삼로 47"));

        companyService.save(new Company("kisri","1818182",1838100871L,
                "한국시설안전연구원","한국.jpg","둘이먹다 하나죽어도 모르는 아메리카노 카페",Category.CAFFE.getDescription()
        ,"울산광역시 동구 전하로 64"));

        companyService.save(new Company("starbucks","starbucks112",1838100871L,
                "별다방","이미지3.jpg","스타벅스를 혐오하는 자만 먹을 수 있는 김치찌개 까페",Category.CAFFE.getDescription()
        ,"전라남도 무안군 해저면 만송로 480"));

        companyService.save(new Company("JeJu","202012",1838100871L,
                "제주공항","이미지4.jpg","백화점이지만 파는건 많지 않아요 하지만 푸드코드 김치찌개가 맛있어요",Category.DEPARTMENT.getDescription()
        ,"제주시 공항로 2"));

    }
}
