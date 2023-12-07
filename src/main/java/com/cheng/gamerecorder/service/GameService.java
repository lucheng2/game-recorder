package com.cheng.gamerecorder.service;

import com.cheng.gamerecorder.dto.BasePageDTO;
import com.cheng.gamerecorder.dto.GameConfigDTO;
import com.cheng.gamerecorder.model.GameConfig;
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

    Page<GameConfig> queryGameConfig(Integer pageNum, Integer pageSize);

    void softDeleteGameConfig(Long id);
}
