package twogtwoj.whereishere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import twogtwoj.whereishere.domain.EventPost;

@Repository
public interface EventPostRepository extends JpaRepository<EventPost, Long> {
}
