package twogtwoj.whereishere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import twogtwoj.whereishere.domain.Member;
import twogtwoj.whereishere.domain.ReviewLike;
import twogtwoj.whereishere.domain.ReviewPost;
import twogtwoj.whereishere.dto.ReviewPostDto;
import twogtwoj.whereishere.file.FileStore;
import twogtwoj.whereishere.file.UploadFile;
import twogtwoj.whereishere.repository.MemberRepository;
import twogtwoj.whereishere.repository.ReviewLikeRepository;
import twogtwoj.whereishere.repository.ReviewPostRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewPostService {

    private final ReviewPostRepository reviewPostRepository;

    private final MemberRepository memberRepository;

    private final ReviewLikeRepository reviewLikeRepository;

    private final FileStore fileStore;


    //글 작성 처리
    public void post(ReviewPostDto reviewPostDto) throws Exception {

        ReviewPost reviewPost = new ReviewPost();

        reviewPost.setReviewPostDate(LocalDate.now());
        reviewPost.setName(reviewPostDto.getName());
        reviewPost.setReviewPostContent(reviewPostDto.getReviewPostContent());
        reviewPost.setReviewPostTitle(reviewPostDto.getReviewPostTitle());

        MultipartFile file1 = reviewPostDto.getFile1();
        MultipartFile file2 = reviewPostDto.getFile2();

        UploadFile uploadFile1 = fileStore.storeFile(file1);
        UploadFile uploadFile2 = fileStore.storeFile(file2);

        reviewPost.setReviewPostImg1(uploadFile1.getStoreFileName());
        reviewPost.setReviewPostImg2(uploadFile2.getStoreFileName());

        reviewPostRepository.save(reviewPost);
    }

    public void update(Long reviewPostId, ReviewPostDto reviewPostDto) throws Exception {
        ReviewPost reviewPost = reviewPostRepository.findById(reviewPostId).get();

        reviewPost.setReviewPostTitle(reviewPostDto.getReviewPostTitle());
        reviewPost.setReviewPostContent(reviewPostDto.getReviewPostContent());//덮어씌우기
        reviewPost.setName(reviewPostDto.getName());
        reviewPost.setReviewPostDate(LocalDate.now());

        // 이미지 파일이 null이 아니라면 수정

        // null이 아니면
        // 기존 파일을 새로운 파일로 변경
        // 1. 기존 파일을 제거
        // 2. 새로운 파일을 등록
        if (reviewPostDto.getFile1().getOriginalFilename().length() != 0) {
            fileStore.updateFile(reviewPost.getReviewPostImg1(), reviewPostDto.getFile1());
        }
        if (reviewPostDto.getFile2().getOriginalFilename().length() != 0) {
            fileStore.updateFile(reviewPost.getReviewPostImg2(), reviewPostDto.getFile2());
        }
        reviewPostRepository.save(reviewPost);
    }

    //게시글 리스트 처리
    public Page<ReviewPost> reviewPostList(Pageable pageable) {
        return reviewPostRepository.findAll(pageable);
    }

    //검색 리스트
    public Page<ReviewPost> reviewPostSearchList(String searchKeyword, String name, Pageable pageable) {
        if (searchKeyword == null) {
            searchKeyword = "";
        }
        if (name == null) {
            name = "";
        }
        return reviewPostRepository.findByReviewPostTitleContainingAndNameContaining(searchKeyword, name, pageable);
    }


    //특정 게시글 불러오기
    public ReviewPost reviewPostView(Long reviewPostId) {
        return reviewPostRepository.findById(reviewPostId).get();
    }

    //특정 게시글 삭제
    public void delete(Long reviewPostId) {
        reviewPostRepository.deleteById(reviewPostId);
    }

    @Transactional
    public int saveReviewLike(ReviewPost reviewPostId, Member memberId) {
        ReviewLike findReviewLike = reviewLikeRepository.findByReviewPostAndMember(reviewPostId, memberId);

        if (findReviewLike == null) {
            Member member = memberRepository.findMemberByMemberId(memberId.getId()).get();
            ReviewPost reviewPost = reviewPostRepository.findById(reviewPostId.getReviewPostId()).get();

            ReviewLike reviewLike = ReviewLike.toReviewLike(member, reviewPost);
            reviewLikeRepository.save(reviewLike);
            reviewPostRepository.plusReviewLike(reviewPostId.getReviewPostId());
            //저장 : 1
            return 1;
        } else {
            reviewLikeRepository.deleteByReviewPostAndMember(reviewPostId, memberId);
            reviewPostRepository.minusReviewLike(reviewPostId.getReviewPostId());
            //삭제 : 0
            return 0;

        }
    }
}