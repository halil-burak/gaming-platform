package com.turkcell.playcell.gamingplatform.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.playcell.gamingplatform.common.entity.GameSlug;

import java.util.List;

@Repository
public interface GameSlugRepository extends JpaRepository<GameSlug, Long> {

    GameSlug findFirst1ByGameDetailTranslation_IdOrderByCreatedDateDesc(Long gdtId);

    List<GameSlug> findByUrl(String url);
}
