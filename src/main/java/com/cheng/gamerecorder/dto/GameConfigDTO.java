package com.cheng.gamerecorder.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 游戏设置DTO
 *
 * @author ChengLu
 * @date 2023/12/7
 */
@Data
public class GameConfigDTO {

    /**
     * 主键ID
     */
    @Id
    private Long id;

    /**
     * 游戏类型名
     */
    @NotBlank
    private String gameType;

    /**
     * 游戏名
     */
    @NotBlank
    private String gameName;

    /**
     * 游戏玩家
     */
    @NotEmpty
    private List<String> players = new ArrayList<>();

}
