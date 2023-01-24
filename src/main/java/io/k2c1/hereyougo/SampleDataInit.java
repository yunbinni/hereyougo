package io.k2c1.hereyougo;

import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

/**
 * 개발단계에 쓰일 테스트용 샘플 리소스들을 만들어봤습니다! (잘했죠?)
 */

@Slf4j
@RequiredArgsConstructor // 리포지토리 생성자
@Component
public class SampleDataInit {

    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final PostRepository postRepository;

    @PostConstruct
    public void init()
    {
        log.info("SAMPLE MEMBER SAVED IN REPO");
        memberRepository.save(createNewMember());

        log.info("SAMPLE POST SAVED IN REPO");
        postRepository.save(createNewPost());
    }

    public static Member createNewMember() {
        Member sampleMember = new Member();

        sampleMember.setId(0L);
        sampleMember.setEmail("test@naver.com");
        sampleMember.setPassword("1234");
        sampleMember.setNickname("test");
        sampleMember.setBusinessType("요식업");

        return sampleMember;
    }


    public static Post createNewPost() {
        Post samplePost = new Post();

        samplePost.setId(0L);
        samplePost.setTitle("게시글 제목");
        samplePost.setContent("게시글 내용");
        samplePost.setWidth(10);
        samplePost.setHeight(20);
        samplePost.setDepth(3);
        samplePost.setTimestamp(LocalDateTime.now());
        samplePost.setAddress("서울 마포구 양화로23길 20 1층");

        return samplePost;
    }
}
