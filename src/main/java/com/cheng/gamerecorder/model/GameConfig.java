package com.cheng.gamerecorder.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameConfig extends BaseModel{


    /**
     * 游戏类型
     */
    @ManyToOne
    private GameType gameType;

    /**
     * 游戏名
     */
    private String gameName;

    /**
     * 游戏玩家
     */
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "player_game_config_relation",
            joinColumns = @JoinColumn(name = "game_config_id", nullable = false, referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "player_id", nullable = false, referencedColumnName = "ID")
    )
    private List<Player> players = new ArrayList<>();
}
