package twogtwoj.whereishere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.Member;
import twogtwoj.whereishere.repository.CompanyRepository;
import twogtwoj.whereishere.repository.MemberRepository;


@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;


    public Company save(Company company){
        return companyRepository.save(company);
    }
}
