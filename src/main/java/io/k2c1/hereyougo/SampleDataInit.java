package io.k2c1.hereyougo;

import io.k2c1.hereyougo.domain.*;
import io.k2c1.hereyougo.repository.CategoryRepository;
import io.k2c1.hereyougo.repository.ImageRepository;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * 개발단계에 쓰일 테스트용 샘플 리소스들을 만들어봤습니다! (잘했죠?)
 */

@Slf4j
@RequiredArgsConstructor // 리포지토리 생성자
@Component
public class SampleDataInit {

    public final MemberRepository memberRepository;
    public final PostRepository postRepository;
    public final ImageRepository imageRepository;
    public final CategoryRepository categoryRepository;

    @Autowired
    private DataSource dataSource;

    Address address1 = Address.builder()
            .sido("경기도")
            .sgg("의정부시")
            .doro("경기도 의정부시 상금로 36, 103동 1601호(금오동, 거성아파트)")
            .jibun("경기도 의정부시 금오동 67-1 거성아파트")
            .zipNo("11764")
            .build();

    Address address2 = Address.builder()
            .sido("서울특별시")
            .sgg("종로구")
            .doro("서울특별시 종로구 삼봉로 71(수송동), G타워 2층")
            .jibun("서울특별시 종로구 수송동 156 광화문G타워")
            .zipNo("03150")
            .build();

    Member member1 = new Member("test@naver.com", "1234", "test1", address1);
    Member member2 = new Member("test2@naver.com", "1234", "test2", address2);

    private static long MEMBER_LID = 0L;
    private static long POST_LID = 0L;

    @PostConstruct
    public void init() throws SQLException {
        ClassPathResource resource = new ClassPathResource("data.sql");

        // Execute the script using the DataSource
        ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);

        memberRepository.save(member1);
        memberRepository.save(member2);
        log.info("SAMPLE MEMBER SAVED IN REPO");

        postRepository.save(createNewPost1());
        postRepository.save(createNewPost2());
        postRepository.save(createNewPost3());
        postRepository.save(createNewPost4());
        log.info("SAMPLE POST SAVED IN REPO");
    }

    Post createNewPost1()
    {
        Post post = Post.builder()
                .writer(member1)
                .title("고깃집 의자 팔아요")
                .content("10개 있습니다.")
                .views(0)
                .quantity(10)
                .reservationQuantity(0)
                .recommend(5)
                .address(member1.getAddress())
                .category(categoryRepository.findById(4L).get())
                .timestamp(LocalDateTime.now())
                .build();

//        Image image = new Image("post1.jpg", "post1.jpg", post);
//        imageRepository.save(image);

        return post;
    }

    public Post createNewPost2()
    {
        Post post = Post.builder()
                .writer(member1)
                .title("PC방 중고PC 판매, 출장조립도 해드려요.")
                .content("설치문의는 01012345678, Windows 클린설치 해드립니다.")
                .views(5)
                .quantity(12)
                .reservationQuantity(0)
                .recommend(10)
                .address(member1.getAddress())
                .category(categoryRepository.findById(33L).get())
                .timestamp(LocalDateTime.now())
                .build();

//        Image image = new Image("post2.jpg", "post2.jpg", post);
//        imageRepository.save(image);

        return post;
    }

    public Post createNewPost3()
    {
        Post post = Post.builder()
                .writer(member2)
                .title("치킨 후라이기 급처")
                .content("3년 정도 사용했고, 유지관리 꾸준히해서 그런지 아마 앞으로도 오래 쓰실 거예요^^")
                .views(0)
                .quantity(13)
                .reservationQuantity(0)
                .recommend(12)
                .address(member2.getAddress())
                .category(categoryRepository.findById(2L).get())
                .timestamp(LocalDateTime.now())
                .build();

//        Image image = new Image("post3.jpg", "post3.jpg", post);
//        imageRepository.save(image);
//
//        post.addImage(image);ㅓㅐㅑ

        return post;
    }

    public Post createNewPost4()
    {
        Post post = Post.builder()
                .writer(member2)
                .title("펫샵 케이지")
                .content("소형 반려동물 전용이구요. 은근 무거워서 택배는 안됩니다!")
                .views(0)
                .quantity(18)
                .reservationQuantity(0)
                .recommend(20)
                .address(member2.getAddress())
                .category(categoryRepository.findById(39L).get())
                .timestamp(LocalDateTime.now())
                .build();

//        Image image = new Image("post4.jpg", "post4.jpg", post);
//        imageRepository.save(image);
//
//        post.addImage(image);

        return post;
    }
}
