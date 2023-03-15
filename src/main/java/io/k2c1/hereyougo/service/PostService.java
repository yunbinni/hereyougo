package io.k2c1.hereyougo.service;

import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.dto.post.PostMarkerDTO;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public List<PostMarkerDTO> getNearPosts(Member member) {
        return postRepository.findByAddressSidoAndAddressSgg(member.getAddress().getSido(), member.getAddress().getSgg()).stream()
                .map(post -> new PostMarkerDTO(post.getId(), post.getTitle(), post.getAddress().getDoro()))
                .collect(Collectors.toList());
    }

    public List<Post> getRecentPopularPosts()
    {
        return postRepository.findAll().stream()
                .sorted(Comparator.comparing((Post p) -> p.getId()).reversed())
                .filter(post -> post.getRecommend() >= 10)
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll().stream()
                .sorted(Comparator.comparing((Post p) -> p.getId()).reversed())
                .limit(16)
                .collect(Collectors.toList());
    }

    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Page<Post> getPostTable(String sido, String sgg, Long categoryId, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 16, Sort.by(Sort.Direction.DESC, "Id"));
        return postRepository.findByAddressSidoAndAddressSggAndCategory_Id(sido, sgg, categoryId, pageRequest);
    }

    public void deleteByWriter(Long memberId){
        Member member = memberRepository.findById(memberId).get();
        postRepository.deleteByWriter(member);
    }

    public Optional<Post> getPost(Long postId){
        return postRepository.findById(postId);
    }

    public int updateView(Long Id) {
        return postRepository.updateViews(Id);
    }

    public int updateRecommend(Long Id) {
        return postRepository.updateRecommend(Id);
    }
}
