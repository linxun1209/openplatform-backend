package com.xingchen.openplatform.service.impl.inner;


import com.xingchen.common.service.InnerUserInterfaceInfoService;
import com.xingchen.openplatform.service.UserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        return true;
        return userInterfaceInfoService.invokeCount(interfaceInfoId, userId);
    }
}
