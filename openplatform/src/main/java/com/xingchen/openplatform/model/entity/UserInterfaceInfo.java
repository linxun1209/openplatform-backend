package com.xingchen.openplatform.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author xingchen
 * @version V1.0
 * @Package com.xingchen.openplatform.model.entity
 * @date 2022/11/26 15:16
 */
@TableName(value = "User_interface_info")
@Data
public class UserInterfaceInfo {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 调用者id
     */
    private Long userId;

    /**
     * 接口id
     */
    private Long interfaceInfoId;

    /**
     * 调用总数
     */
    private Long totalNum;

    /**
     * 状态
     */
    private Integer status;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除(0-未删, 1-已删)
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;




}
