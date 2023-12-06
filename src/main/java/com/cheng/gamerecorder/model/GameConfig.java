package com.cheng.gamerecorder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 游戏设置
 *
 * @author ChengLu
 * @date 2023/12/6
 */
@Entity
@Table(name = "game_config")
@Data
@EqualsAndHashCode(callSuper = true)
public class GameConfig extends BaseModel{


    /**
     * 游戏类型
     */
    private GameType gameType;

    /**
     * 游戏名
     */
    private String gameName;

    /**
     * 游戏玩家
     */
    private List<Player> players = new ArrayList<>();
}
