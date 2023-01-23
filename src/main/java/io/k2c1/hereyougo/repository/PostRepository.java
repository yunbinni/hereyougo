package io.k2c1.hereyougo.repository;

import io.k2c1.hereyougo.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>
{
    List<Post> findByWriter_Id(Long memberId);
}
