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
public class GameRecordSummaryVO {

    /**
     * 玩家姓名
     */
    private String name;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 本局本玩家的游戏总得分
     */
    private Integer totalScore;

    /**
     * 游戏名
     */
    private String gameName;

}
