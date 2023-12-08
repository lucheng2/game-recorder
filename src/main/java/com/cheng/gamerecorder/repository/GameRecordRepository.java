package com.cheng.gamerecorder.repository;

import com.cheng.gamerecorder.model.GameRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * repo
 *
 * @author ChengLu
 * @date 2023/12/7
 */
@Repository
public interface GameRecordRepository extends BaseJpaRepository<GameRecord, Long> {
    @Query("select max(e.gameSetId) from GameRecord e where e.gameConfig.id = ?1")
    Long findMaxGameSetId(Long gameConfigId);

    @Query("select e from GameRecord e where e.gameConfig.id =?1 and e.player.id =?2 and e.gameSetId = ?3")
    Optional<GameRecord> findByGameConfigIdAndPlayerIdAndGameSetId(Long gameConfigId, Long playerId, Long gameSetId);

    List<GameRecord> findAllByGameConfigIdAndGameSetId(Long gameConfigId, Long gameSetId);

    List<GameRecord> findAllByGameConfigId(Long gameConfigId);

    List<GameRecord> findAllByGameConfigIdAndPlayerId(Long gameConfigId, Long playerId);
}
