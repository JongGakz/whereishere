package twogtwoj.whereishere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import twogtwoj.whereishere.domain.ReviewLike;
import twogtwoj.whereishere.repository.ReviewLikeRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewLikeService {

    private final ReviewLikeRepository reviewLikeRepository;

    public List<ReviewLike> findAll() {
        return reviewLikeRepository.findAll();

    }
}
