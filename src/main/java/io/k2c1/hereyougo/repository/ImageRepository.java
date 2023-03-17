package io.k2c1.hereyougo.repository;

import io.k2c1.hereyougo.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {
    void deleteByStoredFilename(String filename);
}
