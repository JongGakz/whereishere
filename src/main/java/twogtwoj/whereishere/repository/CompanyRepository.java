package twogtwoj.whereishere.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import twogtwoj.whereishere.domain.Company;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Repository
public class CompanyRepository {

    private final EntityManager em;

    public Company save(Company company) {
        em.persist(company);
        return company;
    }

    public List<Company> findAll() {
        return em.createQuery("select c from Company c", Company.class).getResultList();
    }

    public Company findCompanyByCompanyName(String companyName) {
        return (Company)(findAll().stream().filter(n -> n.getCompanyName().equals(companyName)).toArray()[0]);
    }
}
