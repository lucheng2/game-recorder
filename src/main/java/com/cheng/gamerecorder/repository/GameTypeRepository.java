package com.cheng.gamerecorder.repository;

import com.cheng.gamerecorder.model.GameType;
import org.springframework.stereotype.Repository;

/**
 * repo
 *
 * @author ChengLu
 * @date 2023/12/7
 */
@Repository
public interface GameTypeRepository extends BaseJpaRepository<GameType, Long>{

    GameType findByGameType(String gameType);

}
