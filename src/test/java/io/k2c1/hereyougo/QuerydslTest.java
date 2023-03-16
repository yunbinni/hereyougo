package io.k2c1.hereyougo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.k2c1.hereyougo.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static io.k2c1.hereyougo.domain.QMember.member;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QuerydslTest {

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory;

    /**
     * member1, member2는 이미 @PostConstruct로 DB에 SAVE 시켜놓았다. (SampleDataInit)
     */
    @BeforeEach
    void before() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    void 회원찾기() {
        List<Member> findMembers = queryFactory
                .selectFrom(member)
                .fetch();

        assertThat(findMembers.size()).isEqualTo(2);
    }

    @AfterEach
    void after() {
        em.flush();
        em.clear();
    }
}
