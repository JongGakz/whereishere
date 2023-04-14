package twogtwoj.whereishere.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import twogtwoj.whereishere.domain.Company;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Transactional
@Repository
public class CompanyRepository {

    private final EntityManager em;

    public Company save(Company company) {
        company.setCompanyLoginPw(new  BCryptPasswordEncoder().encode(company.getCompanyLoginPw()));
        em.persist(company);
        return company;
    }

    public List<Company> findAll() {
        return em.createQuery("select c from Company c", Company.class).getResultList();
    }

    public Company findCompanyByCompanyName(String companyName) {
        return (Company)(findAll().stream().filter(n -> n.getCompanyName().equals(companyName)).toArray()[0]);
    }

    // Optional : jpa null 인셉션 막기용
    // 멤버 테이블에 있는 로그인id 찾기 // 값이 있을 경우에만 true
    public Optional<Company> findByLonginId(String loginId){ // 클라에게 받은 로그인 id(html에서 전달 받은)
        return findAll().stream()
                .filter(company ->company.getCompanyLoginId()
                        .equals(loginId)).findFirst();
    }
}
