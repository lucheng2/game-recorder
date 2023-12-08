package com.cheng.gamerecorder.service;

import com.cheng.gamerecorder.dto.GameConfigDTO;
import com.cheng.gamerecorder.dto.GameRecordDTO;
import com.cheng.gamerecorder.model.GameConfig;
import com.cheng.gamerecorder.model.GameRecord;
import com.cheng.gamerecorder.vo.GameRecordSummaryVO;
import com.cheng.gamerecorder.vo.GameRecordVO;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * service
 *
 * @author ChengLu
 * @date 2023/12/7
 */
public interface GameService {

    GameConfig saveGameConfig(@NotNull GameConfigDTO dto);

    List<GameConfig> findAllGameConfig();

    Page<GameConfig> queryGameConfig(@NotNull Integer pageNum, @NotNull Integer pageSize);

    void softDeleteGameConfig(@NotNull Long id);

    void saveGameRecord(@NotNull GameRecordDTO gameRecordDTO);

    List<List<GameRecordVO>> queryGameConfigByGameConfigId(@NotNull Long gameConfigId);

    List<GameRecordSummaryVO> queryGameRecordSummary(@NotNull Long gameConfigId);
}
