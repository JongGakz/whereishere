package twogtwoj.whereishere.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import twogtwoj.whereishere.domain.Company;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Repository
public class CompanyRepositoryImpl {

    private final PasswordEncoder passwordEncoder;

    private final EntityManager em;

    public Company save(Company company) {
        company.setLoginPw(passwordEncoder.encode(company.getLoginPw()));
        em.persist(company);
        return company;
    }

    public List<Company> findAll() {
        return em.createQuery("select c from Company c", Company.class).getResultList();
    }

    public Company findCompanyByCompanyName(String companyName) {
        return (Company)(findAll().stream().filter(n -> n.getName().equals(companyName)).toArray()[0]);
    }

    public Company findCompanyByCompanyId(Long companyId) {
        return em.find(Company.class,companyId);
    }

    public Company findCompanyByLoginId(String userid) {
        return (Company)(findAll().stream().filter(n -> n.getLoginId().equals(userid)).toArray()[0]);
    }
}
