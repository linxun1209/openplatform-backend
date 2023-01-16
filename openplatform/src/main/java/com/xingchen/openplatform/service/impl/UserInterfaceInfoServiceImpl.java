package com.xingchen.openplatform.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xingchen.openplatform.common.ErrorCode;
import com.xingchen.openplatform.exception.BusinessException;
import com.xingchen.openplatform.model.entity.InterfaceInfo;
import com.xingchen.openplatform.model.entity.UserInterfaceInfo;
import com.xingchen.openplatform.service.UserInterfaceInfoService;
import com.xingchen.openplatform.mapper.UserInterfaceInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author xing'chen
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2022-11-26 15:15:32
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService{


    /**
     * 校验
     *
     * @param userInterfaceInfo
     * @param add               是否为创建校验
     */
    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if(userInterfaceInfo==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if(add){
            if(userInterfaceInfo.getInterfaceInfoId()<=0||userInterfaceInfo.getUserId()<=0){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"接口或者用户不存在");
            }
        }
        if(userInterfaceInfo.getTotalNum()<=0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"剩余次数不能小于0");
        }
    }

    /**
     * 调用接口统计
     *
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    @Override
    public int invokeCount(long interfaceInfoId, long userId) {
        if(interfaceInfoId<=0||userId<=0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UpdateWrapper<UserInterfaceInfo> userInterfaceInfoUpdateWrapper=new UpdateWrapper<>();
        userInterfaceInfoUpdateWrapper.eq("interfaceInfoId",interfaceInfoId);
        userInterfaceInfoUpdateWrapper.eq("userId",userId);
        userInterfaceInfoUpdateWrapper.setSql("leftNum=leftNum-1,totalNum=totalNum+1");
        this.update(userInterfaceInfoUpdateWrapper);
        return 0;
    }
}




