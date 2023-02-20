package io.k2c1.hereyougo.repository;

import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>
{
    List<Post> findByWriter_Id(Long memberId);
    @Modifying
    @Query("delete from Post p where p.writer = :writer ")
    void deleteByWriter(@Param("writer") Member member);
}
