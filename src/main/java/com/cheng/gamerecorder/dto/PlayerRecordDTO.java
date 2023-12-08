package com.cheng.gamerecorder.dto;

import lombok.Data;

/**
 * 玩家得分
 *
 * @author ChengLu
 * @date 2023/12/8
 */
@Data
public class PlayerRecordDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 玩家id
     */
    private Long playerId;

    /**
     * 得分
     */
    private Integer score;

}
