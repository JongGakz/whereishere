package twogtwoj.whereishere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import twogtwoj.whereishere.domain.EventPost;
import twogtwoj.whereishere.repository.EventPostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventPostService {

    private final EventPostRepository eventPostRepository;

    public void save(EventPost eventPost){
        eventPostRepository.save(eventPost);
    }

    // 게시글 리스트 처리
    public Page<EventPost> eventPostList(Pageable pageable) {
        return eventPostRepository.findAll(pageable);
    }

    public List<EventPost> findAll() {
        return eventPostRepository.findAll();
    }

    public EventPost eventPostView(Long eventPostId) {
        return eventPostRepository.findById(eventPostId).get();
    }


    //검색
    public Page<EventPost> eventPostSearchList(String searchContent, Pageable pageable) {
        Page<EventPost> events = eventPostList(pageable);

        Page<EventPost> eventPosts = PageableExecutionUtils.getPage(
                events.filter(n -> n.getCompany().getName().contains(searchContent))
                        .stream().collect(Collectors.toList()), pageable,
                () -> events.filter(n -> n.getCompany().getName().contains(searchContent)).stream().count());
        return eventPosts;
    }

    //삭제
    public void eventPostDelete(Long eventPostId) {
        EventPost eventPost = eventPostRepository.findById(eventPostId).get();
        eventPostRepository.delete(eventPost);
    }
}
