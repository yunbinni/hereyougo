package io.k2c1.hereyougo.repository;

import io.k2c1.hereyougo.domain.FavoriteCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteCategoryRepository extends JpaRepository<FavoriteCategory, Long> {
    List<FavoriteCategory> findByMember_id(Long memberId);
}
