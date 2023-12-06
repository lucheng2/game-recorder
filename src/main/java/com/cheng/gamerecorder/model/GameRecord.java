package com.cheng.gamerecorder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 游戏记录
 *
 * @author ChengLu
 * @date 2023/12/6
 */
@Entity
@Table(name = "game_record")
@Data
@EqualsAndHashCode(callSuper = true)
public class GameRecord extends BaseModel{

    /**
     * 局数id
     */
    private Long setId;

    /**
     * 游戏
     */
    private GameConfig gameConfig;

    /**
     * 玩家
     */
    private Player player;

    /**
     * 本局本玩家的得分
     */
    private Integer score;

    /**
     * 本局本玩家的游戏总得分
     */
    private Integer totalScore;
}
