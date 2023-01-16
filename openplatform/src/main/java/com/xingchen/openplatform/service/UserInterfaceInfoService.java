package com.xingchen.openplatform.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xingchen.openplatform.model.entity.InterfaceInfo;
import com.xingchen.openplatform.model.entity.User;
import com.xingchen.openplatform.model.entity.UserInterfaceInfo;

/**
* @author xing'chen
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2022-11-26 15:15:32
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    /**
     * 校验
     *
     * @param userInterfaceInfo
     * @param add 是否为创建校验
     */
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);


    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    int invokeCount(long interfaceInfoId,long userId);


}
