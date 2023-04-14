package twogtwoj.whereishere.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.Member;
import twogtwoj.whereishere.domain.Star;
import twogtwoj.whereishere.repository.StarRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StarService {

    private final StarRepository starRepository;

    public Star save(Star star){
        return starRepository.save(star);
    }

    public Double findStarsPointByCompany(Company company) {
        return starRepository.findStarsPointByCompanyId(company);
    }

    public List<Star> findAll() {
        return starRepository.findAll();
    }
}
