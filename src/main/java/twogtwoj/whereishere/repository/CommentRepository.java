package twogtwoj.whereishere.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import twogtwoj.whereishere.domain.Comment;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.ReviewPost;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Transactional
@Repository
public class CommentRepository {
    private final EntityManager em;

    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c", Comment.class).getResultList();
    }


    public List<Comment> findCommentListByCompany(Company company) {
        return findAll().stream().filter(n -> n.getCompany().equals(company)).collect(Collectors.toUnmodifiableList());
    }

    public Comment save(Comment comment) {
        if (comment.getCommentId() == null) {
            em.persist(comment);
        } else {
            em.merge(comment);
        }
        return comment;
    }
}
