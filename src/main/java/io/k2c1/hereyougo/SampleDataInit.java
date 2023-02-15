package io.k2c1.hereyougo;

import io.k2c1.hereyougo.domain.Address;
import io.k2c1.hereyougo.domain.Category;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.repository.PostRepository;
import io.k2c1.hereyougo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    Address address = new Address("경기도", "의정부시", "경기도 의정부시 상금로 36, 103동 1601호(금오동, 거성아파트)", "경기도 의정부시 금오동 67-1 거성아파트", "11764");
    Member member = new Member("test@naver.com", "1234", "testNickname", "요식업", address);

    private static long MEMBER_LID = 0L;
    private static long POST_LID = 0L;

    @PostConstruct
    public void init() {
        memberRepository.save(member);
        log.info("SAMPLE MEMBER SAVED IN REPO");

        postRepository.save(createNewPost1());
        postRepository.save(createNewPost2());
        postRepository.save(createNewPost3());
        postRepository.save(createNewPost4());
        log.info("SAMPLE POST SAVED IN REPO");
    }

    public Post createNewPost1()
    {
        return new Post(
                member,
                "테스트 제목 1",
                "테스트 내용 1",
                "10 * 20 * 30",
                0,
                3,
                11,
                address,
                LocalDateTime.now()
        );
    }

    public Post createNewPost2()
    {
        return new Post(
                member,
                "테스트 제목 2",
                "테스트 내용 2",
                "10 * 20 * 30",
                0,
                3,
                12,
                address,
                LocalDateTime.now()
        );
    }

    public Post createNewPost3()
    {
        return new Post(
                member,
                "테스트 제목 3",
                "테스트 내용 3",
                "10 * 20 * 30",
                0,
                3,
                13,
                address,
                LocalDateTime.now()
        );
    }

    public Post createNewPost4()
    {
        return new Post(
                member,
                "테스트 제목 4",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It h",
                "10 * 20 * 30",
                0,
                3,
                14,
                address,
                LocalDateTime.now()
        );
    }
}
