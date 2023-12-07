package com.cheng.gamerecorder.repository;

import com.cheng.gamerecorder.model.GameRecord;
import org.springframework.stereotype.Repository;

/**
 * repo
 *
 * @author ChengLu
 * @date 2023/12/7
 */
@Repository
public interface GameRecordRepository extends BaseJpaRepository<GameRecord, Long> {

}
