package com.turkcell.playcell.gamingplatform.common.repository;

import com.turkcell.playcell.gamingplatform.common.entity.SliderImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SliderImageRepository extends JpaRepository<SliderImage, Long> {
    SliderImage findByImageId(Long imageId);
}
