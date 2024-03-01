package com.xingchen.openplatform.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingchen.common.model.entity.InterfaceInfo;
import com.xingchen.common.model.entity.User;
import com.xingchen.common.model.entity.UserInterfaceInfo;
import com.xingchen.openplatform.common.ErrorCode;
import com.xingchen.openplatform.exception.BusinessException;
import com.xingchen.openplatform.mapper.InterfaceInfoMapper;
import com.xingchen.openplatform.model.vo.InterfaceInfoVO;
import com.xingchen.openplatform.service.InterfaceInfoService;
import com.xingchen.openplatform.service.UserInterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xingchen
 * @description 针对表【InterfaceInfo(帖子)】的数据库操作Service实现
 */
@Service
public class InterfaceInfoImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo> implements InterfaceInfoService {

   @Resource
   private UserInterfaceInfoService UserInterfaceInfoService;

    /**
     *
     * @param interfaceInfo
     * @param add 是否为创建校验
     */
    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
    }

    @Override
    public InterfaceInfoVO getInterfaceInfoById(long id, User loginUser) {
        InterfaceInfo interfaceInfo = this.getById(id);
        if (interfaceInfo == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"接口不存在");
        }
        InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
        BeanUtils.copyProperties(interfaceInfo,interfaceInfoVO);
        //查询该用户剩余调用接口次数
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("interfaceInfoId",id);
        queryWrapper.eq("userId",loginUser.getId());
        UserInterfaceInfo userInterfaceInfo = UserInterfaceInfoService.getOne(queryWrapper);
        if (userInterfaceInfo != null){
            interfaceInfoVO.setLeftNum(userInterfaceInfo.getLeftNum());
            interfaceInfoVO.setTotalNum(userInterfaceInfo.getTotalNum());
        }
        return interfaceInfoVO;
    }
}




