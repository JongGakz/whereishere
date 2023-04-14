package twogtwoj.whereishere.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.Star;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
@RequiredArgsConstructor
public class StarRepository {

    private final EntityManager em;

    public Star save(Star star) {
        if (star.getStarId() == null) {
            em.persist(star);
        } else {
            em.merge(star);
        }
        return star;
    }

    public List<Star> findAll(){
        return em.createQuery("select s from Star s", Star.class).getResultList();
    }
    public Double findStarsPointByCompanyId(Company company) {
        Double starPoint = 0.0;
        List<Star> stars = findAll().stream().filter(n -> n.getCompany().equals(company)).collect(Collectors.toUnmodifiableList());
        for (int i = 0; i < stars.size(); i++) {
            starPoint += stars.get(i).getStarPoint();
        }
        return  starPoint/stars.size();
    }
}
