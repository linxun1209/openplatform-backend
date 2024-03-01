package com.xingchen.openplatform.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xingchen.common.model.entity.UserInterfaceInfo;

/**
 *
 */
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * 调用接口后增加数据库中的调用次数
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);


    /**
     * 增加接口调用次数
     *
     * @param interfaceInfoId
     * @param userId
     * @param leftNum
     * @return
     */
    boolean addInvokeTimes(long interfaceInfoId, long userId, int leftNum);



}
