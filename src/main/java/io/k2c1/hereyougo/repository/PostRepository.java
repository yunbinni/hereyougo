package io.k2c1.hereyougo.repository;

import io.k2c1.hereyougo.domain.Address;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>
{
    List<Post> findByWriter_Id(Long memberId);

    List<Post> findByAddressSidoAndAddressSgg(String sido, String sgg);

    @Modifying
    @Query("delete from Post p where p.writer = :writer ")
    void deleteByWriter(@Param("writer") Member member);

    @Modifying
    @Query("update Post p set p.views = p.views + 1 where p.id = :id")
    int updateViews(@Param("id") Long Id);

    @Modifying
    @Query("update Post p set p.recommend = p.recommend + 1 where p.id = :id")
    int updateRecommend(@Param("id") Long Id);

    List<Post> findFirst10ByWriter_Id(Long memberId);
}
