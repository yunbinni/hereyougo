package io.k2c1.hereyougo.repository;

import io.k2c1.hereyougo.domain.Category;
import io.k2c1.hereyougo.domain.FavoriteCategory;
import io.k2c1.hereyougo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavoriteCategoryRepository extends JpaRepository<FavoriteCategory, Long> {
    List<FavoriteCategory> findByMember_id(Long memberId);

    Optional<FavoriteCategory> findByIdAndMember_id(Long favoriteCategoryId, Long memberId);

//    @Query(value = "SELECT fc FROM FavoriteCategory fc WHERE fc.favoriteCategoryId = :favoriteCategoryId and fc.member_id = : memberId")
//    Optional<FavoriteCategory> existFavoriteCategory(@Param("favoriteCategoryId") Long favoriteCategoryId , @Param("member_id") Long member_id);

}
