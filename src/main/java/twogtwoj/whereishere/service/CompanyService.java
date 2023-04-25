package twogtwoj.whereishere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.repository.CompanyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;


    public List<Company> findByNameContaining(String name) {
        return companyRepository.findByNameContaining(name);
    }
}
