package io.k2c1.hereyougo.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.k2c1.hereyougo.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static io.k2c1.hereyougo.domain.QPost.post;

@Repository
public class PostSearchRepository {

    private JPAQueryFactory queryFactory;

    public PostSearchRepository(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    public Page<Post> findByConditions(String sido, String sgg, Long categoryId, String searchKey, Pageable pageable) {

        List<Post> content = queryFactory
                .selectFrom(post)
                .where(
                        sidoCond(sido),
                        sggCond(sgg),
                        categoryIdCond(categoryId),
                        searchKeyCond(searchKey)
                )
                .orderBy(post.id.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(post.count())
                .from(post)
                .where(
                        sidoCond(sido),
                        sggCond(sgg),
                        categoryIdCond(categoryId),
                        searchKeyCond(searchKey)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression sidoCond(String sido) {
        return sido.equals("전국") ? null : post.address.sido.eq(sido);
    }

    private BooleanExpression sggCond(String sgg) {
        return sgg.contains("전체") ? null : post.address.sgg.eq(sgg);
    }

    private BooleanExpression categoryIdCond(Long categoryId) {
        if (categoryId == 0L) {
            return null;
        } else if (categoryId >= 1L && categoryId <= 3L) {
            return (post.category.id.eq(categoryId).or(post.category.parent.id.eq(categoryId)));
        } else {
            return post.category.id.eq(categoryId);
        }
    }

    private BooleanExpression searchKeyCond(String searchKey) {
        if(searchKey == null || searchKey.equals(""))
            return null;

        return post.title.contains(searchKey)
                .or(post.content.contains(searchKey))
                .or(post.address.doro.contains(searchKey))
                .or(post.address.jibun.contains(searchKey));
    }
}
