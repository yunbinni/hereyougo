package io.k2c1.hereyougo.service;

import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
                .collect(Collectors.toList());
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
