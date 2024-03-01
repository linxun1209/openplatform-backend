package com.xingchen.common.service;

/**
 *
 * @author xing'chen
 */
public interface InnerUserInterfaceInfoService {

    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);



    /**
     * 调用接口剩余次数，小于等于0次就返回false，大于0返回true
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeLeftNum(long interfaceInfoId, long userId);
}
