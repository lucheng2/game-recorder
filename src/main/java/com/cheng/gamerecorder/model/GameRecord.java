package com.cheng.gamerecorder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameRecord extends BaseModel implements Comparable<GameRecord>{

    /**
     * 局数id
     */
    private Long gameSetId;

    /**
     * 游戏
     */
    @ManyToOne
    private GameConfig gameConfig;

    /**
     * 玩家
     */
    @ManyToOne
    private Player player;

    /**
     * 本局本玩家的得分
     */
    private Integer score;

    /**
     * 本局本玩家的游戏总得分
     */
    private Integer totalScore;

    @Override
    public int compareTo(GameRecord o) {
        return - this.gameSetId.compareTo(o.gameSetId);
    }
}
