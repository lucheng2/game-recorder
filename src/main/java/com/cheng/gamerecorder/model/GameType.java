package com.cheng.gamerecorder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 游戏类型
 *
 * @author ChengLu
 * @date 2023/12/6
 */
@Entity
@Table(name = "game_type")
@Data
@EqualsAndHashCode(callSuper = true)
public class GameType extends BaseModel{


    /**
     * 游戏类型名
     */
    private String gameType;

    /**
     * 玩家人数
     */
    private Integer playerNum;
}
