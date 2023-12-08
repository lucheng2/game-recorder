package com.cheng.gamerecorder.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * vo
 *
 * @author ChengLu
 * @date 2023/12/8
 */
@Data
public class GameRecordVO {

    /**
     * 局数id
     */
    private Long gameSetId;

    /**
     * 玩家姓名
     */
    private String name;

    /**
     * 本局本玩家的得分
     */
    private Integer score;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
