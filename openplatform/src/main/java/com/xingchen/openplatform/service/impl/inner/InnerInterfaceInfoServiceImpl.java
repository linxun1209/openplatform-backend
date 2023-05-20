package com.xingchen.openplatform.service.impl.inner;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.xingchen.common.service.InnerInterfaceInfoService;
import com.xingchen.openplatform.common.ErrorCode;
import com.xingchen.openplatform.exception.BusinessException;
import com.xingchen.openplatform.mapper.InterfaceInfoMapper;

import com.xingchen.common.model.entity.InterfaceInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {

    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;



    @Override
    public InterfaceInfo getInterfaceInfo(String url, String method) {
        if (StringUtils.isAnyBlank(url, method)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url", url);
        queryWrapper.eq("method", method);
        return interfaceInfoMapper.selectOne(queryWrapper);
    }
}
