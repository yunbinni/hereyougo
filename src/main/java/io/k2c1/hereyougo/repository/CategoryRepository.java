package io.k2c1.hereyougo.repository;

import io.k2c1.hereyougo.domain.Category;
import org.hibernate.annotations.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByParentIsNull();

    @Query(value = "SELECT c FROM Category c WHERE parent_id = :parent_id")
    List<Category> getCategoriesByParentId(@Param("parent_id") Long parentId);
}
