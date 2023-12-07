package com.cheng.gamerecorder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 分页查询基础字段
 *
 * @author ChengLu
 * @date 2023/9/18
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BasePageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页码，默认1
     */
    private Integer pageNum = 1;
    /**
     * 页码大小，默认50
     */
    private Integer pageSize = 50;

}
