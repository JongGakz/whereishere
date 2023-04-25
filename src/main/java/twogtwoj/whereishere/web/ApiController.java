package twogtwoj.whereishere.web;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.service.CompanyService;
import twogtwoj.whereishere.service.CompanyServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final CompanyService companyService;
    @GetMapping("/api/search/companies")
    public String searchCompanies(@RequestParam String query) {

        List<Company> list =  companyService.findByNameContaining(query);

        System.out.println(list);
        JSONObject json = new JSONObject();
        json.put("companyList",list);
//        System.out.println(query);
        // 주어진 query 값으로 매칭되는 회사 조회
        // 회사 리스트를 반환
        return json.toString();
    }

    @PostMapping("/api/search/companies")
    public String companies(@RequestParam String query) {
        List<Company> list = companyService.findByNameContaining(query);

        return String.valueOf(list);
    }

}
