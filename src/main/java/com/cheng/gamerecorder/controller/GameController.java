package com.cheng.gamerecorder.controller;

import com.cheng.gamerecorder.dto.GameConfigDTO;
import com.cheng.gamerecorder.dto.GameRecordDTO;
import com.cheng.gamerecorder.model.GameConfig;
import com.cheng.gamerecorder.model.GameRecord;
import com.cheng.gamerecorder.service.GameService;
import com.cheng.gamerecorder.vo.GameRecordSummaryVO;
import com.cheng.gamerecorder.vo.GameRecordVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * controller
 *
 * @author ChengLu
 * @date 2023/12/7
 */
@RestController
@Tag(name = "游戏")
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @Operation(summary = "保存游戏设置")
    @PostMapping("/saveGameConfig")
    public GameConfig saveGameConfig(GameConfigDTO dto) {
        return gameService.saveGameConfig(dto);
    }

    @Operation(summary = "查询所有游戏设置")
    @GetMapping("/findAllGameConfig")
    public List<GameConfig> findAllGameConfig() {
        return gameService.findAllGameConfig();
    }


    @Operation(summary = "删除游戏设置")
    @PostMapping("/softDeleteGameConfig")
    public void softDeleteGameConfig(Long id) {
        gameService.softDeleteGameConfig(id);
    }

    @Operation(summary = "查询游戏设置")
    @GetMapping("/queryGameConfig")
    public List<GameConfig> queryGameConfig(@Parameter(description = "页码", required = true) @RequestParam(defaultValue = "1") Integer pageNum,
                                            @Parameter(description = "页大小", required = true) @RequestParam(defaultValue = "50") Integer pageSize) {
        return gameService.queryGameConfig(pageNum, pageSize).getContent();
    }

    @Operation(summary = "查询游戏记录")
    @GetMapping("/queryGameRecord")
    public List<List<GameRecordVO>> queryGameRecord(@Parameter(description = "游戏配置id", required = true) @RequestParam Long gameConfigId) {
        return gameService.queryGameConfigByGameConfigId(gameConfigId);
    }

    @Operation(summary = "保存游戏记录")
    @PostMapping("/saveGameRecord")
    public void saveGameRecord(@RequestBody GameRecordDTO gameRecordDTO) {
        gameService.saveGameRecord(gameRecordDTO);
    }

    @Operation(summary = "查询游戏记录汇总")
    @GetMapping("/queryGameRecordSummary")
    public List<GameRecordSummaryVO> queryGameRecordSummary(@Parameter(description = "游戏配置id", required = true) @RequestParam Long gameConfigId) {
        return gameService.queryGameRecordSummary(gameConfigId);
    }
}
