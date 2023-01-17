package com.xingchen.openplatform.service.impl;



import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xingchen.openplatform.common.ErrorCode;
import com.xingchen.openplatform.exception.BusinessException;
import com.xingchen.openplatform.model.entity.InterfaceInfo;
import com.xingchen.openplatform.model.entity.UserInterfaceInfo;
import com.xingchen.openplatform.service.UserInterfaceInfoService;
import com.xingchen.openplatform.mapper.UserInterfaceInfoMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
* @author xing'chen
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2022-11-26 15:15:32
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
        implements UserInterfaceInfoService {


    @Override
    public void validUserInterfaceInfo(com.xingchen.common.model.entity.UserInterfaceInfo userInterfaceInfo, boolean add) {

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
        return 0;
    }
}