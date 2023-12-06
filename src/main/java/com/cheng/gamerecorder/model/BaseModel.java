package com.cheng.gamerecorder.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * 基础类
 * @author ChengLu
 * @date 2023/9/20
 */
@Data
@MappedSuperclass
public abstract class BaseModel {

    /**
     * 主键ID
     */
    @Id
    private Long id;
    /**
     * 创建时间
     */
    @CreatedDate
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @LastModifiedDate
    private LocalDateTime updateTime;
    /**
     * 创建人编号
     */
    @CreatedBy
    private String createBy;
    /**
     * 修改人编号
     */
    @LastModifiedBy
    private String updateBy;

}