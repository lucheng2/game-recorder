package com.cheng.gamerecorder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

/**
 * 游戏玩家
 *
 * @author ChengLu
 * @date 2023/12/6
 */
@Entity
@Table(name = "player")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player extends BaseModel{

    /**
     * 玩家姓名
     */
    private String name;
}
