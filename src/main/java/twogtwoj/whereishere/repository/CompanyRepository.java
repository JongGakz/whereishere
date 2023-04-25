package twogtwoj.whereishere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import twogtwoj.whereishere.domain.Company;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    // 이름이 완전 100퍼 일치시
    List<Company> findByNameContaining(String name);
}
