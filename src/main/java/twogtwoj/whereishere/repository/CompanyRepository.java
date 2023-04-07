package twogtwoj.whereishere.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import twogtwoj.whereishere.domain.Company;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Repository
public class CompanyRepository {

    private final EntityManager em;

    public Company save(Company company) {
        em.persist(company);
        return company;
    }
}
