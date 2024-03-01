package com.xingchen.openplatform.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingchen.common.model.entity.UserInterfaceInfo;
import com.xingchen.openplatform.common.ErrorCode;
import com.xingchen.openplatform.exception.BusinessException;
import com.xingchen.openplatform.mapper.UserInterfaceInfoMapper;
import com.xingchen.openplatform.service.UserInterfaceInfoService;
import org.springframework.stereotype.Service;

/**
* @author xing'chen
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2022-11-26 15:15:32
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
        implements UserInterfaceInfoService {

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();
        Long userId = userInterfaceInfo.getUserId();
        Integer leftNum = userInterfaceInfo.getLeftNum();
        //创建时参数不为空
        if (add) {
            if (interfaceInfoId <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口不存在");
            }
            if (userId <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在");
            }
        }
        if (leftNum < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不能小于0");
        }
    }


    /**
     * 记录调用接口次数
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        if (interfaceInfoId <= 0 || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //老用户调用接口
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("interfaceInfoId",interfaceInfoId);
        updateWrapper.eq("userId",userId);
        //剩余调用次数大于0
        updateWrapper.gt("leftNum",0);
        updateWrapper.setSql("leftNum = leftNum -1,totalNum = totalNum+1");
        return this.update(updateWrapper);
    }


    @Override
    public boolean addInvokeTimes(long interfaceInfoId, long userId, int leftNum) {

        //查询接口信息
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",userId);
        queryWrapper.eq("interfaceInfoId",interfaceInfoId);
        UserInterfaceInfo oldUserInterfaceInfo = this.getOne(queryWrapper);
        if (oldUserInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        //查询剩余调用次数
        int oldLeftNum = oldUserInterfaceInfo.getLeftNum();
        leftNum+=oldLeftNum;
        //在原调用次数上增加调用次数
        oldUserInterfaceInfo.setLeftNum(leftNum);
        return this.updateById(oldUserInterfaceInfo);
    }

}