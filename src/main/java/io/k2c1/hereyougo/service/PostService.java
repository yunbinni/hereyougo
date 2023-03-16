package io.k2c1.hereyougo.service;

import io.k2c1.hereyougo.domain.Address;
import io.k2c1.hereyougo.domain.Category;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.dto.post.PostMarkerDTO;
import io.k2c1.hereyougo.dto.post.PostUpdateForm;
import io.k2c1.hereyougo.repository.CategoryRepository;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
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

    private final CategoryRepository categoryRepository;

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
                .limit(8)
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

    public void updatePost(PostUpdateForm updateForm, Member loginMember){
        Address address;
        Long postId = updateForm.getPostId();
        Post post = postRepository.findById(postId).get();
        Long categoryId = updateForm.getCategoryId();
        Category category = categoryRepository.findById(categoryId).get();

        String title = updateForm.getTitle();
        String content = updateForm.getContent();
        int quantity = updateForm.getQuantity();

        if (updateForm.getRoadAddrPart1() == null || updateForm.getRoadAddrPart1().equals("")) {
            address = loginMember.getAddress();
        } else {
            address = Address.builder()
                    .sido(updateForm.getSiNm())
                    .sgg(updateForm.getSggNm())
                    .doro(updateForm.getRoadFullAddr())
                    .jibun(updateForm.getJibunAddr())
                    .zipNo(updateForm.getZipNo())
                    .build();
        }

        post.updatePostInfo(title, content, quantity ,address, category);

    }
}
