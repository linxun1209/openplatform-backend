package com.xingchen.openplatform.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xingchen.common.model.entity.InterfaceInfo;
import com.xingchen.common.model.entity.User;
import com.xingchen.openplatform.model.vo.InterfaceInfoVO;

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


    /**
     * 根据接口id返回接口封装信息（个人剩余调用次数）
     *
     * @param id
     * @param loginUser
     * @return
     */
    InterfaceInfoVO getInterfaceInfoById(long id, User loginUser);

}
