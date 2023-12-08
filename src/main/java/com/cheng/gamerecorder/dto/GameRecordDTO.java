package com.cheng.gamerecorder.dto;

import lombok.Data;

import java.util.List;

/**
 * 游戏记录dto
 *
 * @author ChengLu
 * @date 2023/12/7
 */
@Data
public class GameRecordDTO {

    /**
     * 游戏配置id
     */
    private Long gameConfigId;

    /**
     * 玩家记录
     */
    private List<PlayerRecordDTO> playerRecords;

}
