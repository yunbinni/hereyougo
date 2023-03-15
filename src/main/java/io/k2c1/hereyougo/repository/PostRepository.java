package io.k2c1.hereyougo.repository;

import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>
{
    Page<Post> findByWriter_Id(Long memberId, Pageable pageable);

//    Page<Post> findByWriter_IdAndTitleContainingOrContentContaining(String title, String content, Pageable pageable);

    Page<Post> findAll(Pageable pageable);

    Page<Post> findByCategoryParent_Id(Long parentId, Pageable pageable);

    Page<Post> findByCategoryId(Long categoryId, Pageable pageable);

    Page<Post> findByAddressSido(String sido, Pageable pageable);

    Page<Post> findByAddressSidoAndCategoryParent_Id(String sio, Long categoryId, Pageable pageable);

    Page<Post> findByAddressSidoAndCategoryId(String sido, Long categoryId, Pageable pageable);

    Page<Post> findByAddressSidoAndAddressSgg(String sido, String sgg, Pageable pageable);
    List<Post> findByAddressSidoAndAddressSgg(String sido, String sgg);

    Page<Post> findByAddressSidoAndAddressSggAndCategoryParent_Id(String sido, String sgg, Long categoryId, Pageable pageable);

    Page<Post> findByAddressSidoAndAddressSggAndCategoryId(String sido, String sgg, Long categoryId, Pageable pageable);

    @Modifying
    @Query("delete from Post p where p.writer = :writer ")
    void deleteByWriter(@Param("writer") Member member);

    List<Post> findFirst5ByWriter_Id(Long memberId);
    
    @Modifying
    @Query("update Post p set p.views = p.views + 1 where p.id = :id")
    int updateViews(@Param("id") Long Id);

    @Modifying
    @Query("update Post p set p.recommend = p.recommend + 1 where p.id = :id")
    int updateRecommend(@Param("id") Long Id);
}
