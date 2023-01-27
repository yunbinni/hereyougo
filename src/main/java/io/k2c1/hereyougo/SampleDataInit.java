package io.k2c1.hereyougo;

import io.k2c1.hereyougo.domain.Address;
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
    public final MemberRepository memberRepository;
    @Autowired
    public final PostRepository postRepository;

    private static long MEMBER_LID = 0L;
    private static long POST_LID = 0L;

    @PostConstruct
    public void init()
    {
        memberRepository.save(createNewMember());
        log.info("SAMPLE MEMBER SAVED IN REPO");

        postRepository.save(createNewPost());
        log.info("SAMPLE POST SAVED IN REPO");
    }

    public static Member createNewMember()
    {
        return new Member(MEMBER_LID++, "test@naver.com", "1234", "testNickname", "요식업");
    }


    public static Post createNewPost()
    {
        return new Post(
                POST_LID++,
                createNewMember(),
                "테스트 게시글 제목",
                "테스트 게시글 내용",
                10,
                20,
                30,
                0,
                3,
                "서울 마포구 양화로23길 20 1층",
                LocalDateTime.now()
        );
    }
}
