package io.k2c1.hereyougo.repository;

import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class PostServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostRepository postRepository;

    @Test
    public void 게시글등록()
    {
        Member member = 회원생성();

        Post post = new Post();
        post.setTitle("게시글 제목");
        post.setContent("게시글 내용");
        post.setWriter(member);
        post.setWidth(10);
        post.setHeight(20);
        post.setDepth(3);
        post.setTimestamp(LocalDateTime.now());
        post.setAddress("서울 마포구 양화로23길 20 1층");

        Post result = postRepository.save(post);

        assertEquals(post.getTitle(), result.getTitle());
    }

    @Test
    public void 게시글수정(){
//      given
        Member member = 회원생성();
        Post savedPost = 게시물생성();

//      when
        savedPost.setContent("게시글 내용 수정");
        savedPost.setAddress("서울 마포구 양화로6길 22");
        savedPost.setDepth(5);

        Post result = postRepository.findById(1L).get();

//      then
        assertEquals(savedPost.getContent(), result.getContent());
        assertEquals(savedPost.getAddress(), result.getAddress());
        assertEquals(savedPost.getDepth(), result.getDepth());
    }

    @Test
    public void 게시글조회(){
//      given
        Member member = 회원생성();
        Post post = 게시물생성();

//      when
        Post result = postRepository.findById(1L).get();

//      then
        assertEquals(post.getContent(), result.getContent());
    }

    @Test
    public void 게시글삭제(){
//      given
        Member member = 회원생성();

        List<Post> posts = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            Post post = new Post();
            post.setTitle("제목"+i);
            post.setContent("내용"+i);
            post.setWriter(member);
            postRepository.save(post);
            posts.add(post);
        }

//      when
        postRepository.deleteById(3L);

//      then
        int result = postRepository.findAll().size();

        assertEquals(4, result);
    }


    @Test
    public void 회원별게시글목록조회(){
//      given
        Member member = 회원생성();

//      회원 1명이 게시글 5개 등록
        List<Post> posts = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            Post post = new Post();
            post.setTitle("제목"+i);
            post.setContent("내용"+i);
            post.setWriter(member);
            postRepository.save(post);
            posts.add(post);
        }

//      when
//      멤버 id를 통해 게시물 목록 조회
        List<Post> result = postRepository.findByWriter_Id(member.getId());

//      then
        assertEquals(5, result.size());

    }

    public Member 회원생성(){
        Member member = new Member();
        member.setEmail("test@naver.com");
        member.setPassword("123456");
        member.setNickname("test");

        memberRepository.save(member);

        return member;
    }

    public Post 게시물생성(){
        Post post = new Post();
        Member member = 회원생성();

        post.setTitle("게시글 제목");
        post.setContent("게시글 내용");
        post.setWriter(member);
        post.setWidth(10);
        post.setHeight(20);
        post.setDepth(3);
        post.setTimestamp(LocalDateTime.now());
        post.setAddress("서울 마포구 양화로23길 20 1층");

        postRepository.save(post);

        return post;
    }
}