package com.xingchen.openplatform.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xingchen.openplatform.model.entity.InterfaceInfo;

/**
 * @author xingchen
 * @description 针对表【InterfaceInfo(帖子)】的数据库操作Service
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    /**
     * 校验
     *
     * @param interfaceInfo
     * @param add 是否为创建校验
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

}
