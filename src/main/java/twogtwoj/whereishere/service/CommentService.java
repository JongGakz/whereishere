package twogtwoj.whereishere.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import twogtwoj.whereishere.domain.Comment;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> findCommentListByCompany(Company company) {
        return commentRepository.findCommentListByCompany(company);
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
