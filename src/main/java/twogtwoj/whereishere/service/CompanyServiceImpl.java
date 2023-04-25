package twogtwoj.whereishere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.repository.CompanyRepositoryImpl;
import twogtwoj.whereishere.repository.CompanyRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CompanyServiceImpl {

    private final CompanyRepositoryImpl companyRepositoryImpl;


    public Company save(Company company){
        return companyRepositoryImpl.save(company);
    }

    public List<Company> findAll() {
        return companyRepositoryImpl.findAll();
    }

    public Company findCompanyByCompanyName(String companyName) {
        return companyRepositoryImpl.findCompanyByCompanyName(companyName);
    }

    public Company findCompanyByCompanyId(Long companyId) {
        return companyRepositoryImpl.findCompanyByCompanyId(companyId);
    }

    public Company findCompanyByLoginId(String userId) {
        return companyRepositoryImpl.findCompanyByLoginId(userId);
    }

}
