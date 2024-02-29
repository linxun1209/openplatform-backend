package com.xingchen.openplatform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.xingchen.common.model.entity.InterfaceInfo;
import com.xingchen.common.model.entity.User;
import com.xingchen.common.model.entity.UserInterfaceInfo;
import com.xingchen.openplatform.annotation.AuthCheck;
import com.xingchen.openplatform.common.BaseResponse;
import com.xingchen.openplatform.common.ErrorCode;
import com.xingchen.openplatform.common.ResultUtils;
import com.xingchen.openplatform.exception.BusinessException;
import com.xingchen.openplatform.mapper.UserInterfaceInfoMapper;
import com.xingchen.openplatform.model.vo.InterfaceInfoVO;
import com.xingchen.openplatform.model.vo.UserInterfaceVO;
import com.xingchen.openplatform.service.InterfaceInfoService;
import com.xingchen.openplatform.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 分析控制器
 */
@RestController
@RequestMapping("/analysis")
@Slf4j
public class AnalysisController {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Resource
    private UserService userService;

    /**
     * 接口被调用次数TOP5
     * @return
     */
    @GetMapping("/top/interface/invoke")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<List<InterfaceInfoVO>> listTopInvokeInterfaceInfo() {
        List<UserInterfaceInfo> userInterfaceInfoList = userInterfaceInfoMapper.listTopInvokeInterfaceInfo(3);
        Map<Long, List<UserInterfaceInfo>> interfaceInfoIdObjMap = userInterfaceInfoList.stream()
                .collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", interfaceInfoIdObjMap.keySet());
        List<InterfaceInfo> list = interfaceInfoService.list(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        List<InterfaceInfoVO> interfaceInfoVOList = list.stream().map(interfaceInfo -> {
            InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
            BeanUtils.copyProperties(interfaceInfo, interfaceInfoVO);
            int totalNum = interfaceInfoIdObjMap.get(interfaceInfo.getId()).get(0).getTotalNum();
            interfaceInfoVO.setTotalNum(totalNum);
            return interfaceInfoVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(interfaceInfoVOList);
    }
    /**
     * 最活跃的用户TOP5
     * @return
     */
    @GetMapping("/top/interface/user")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<List<UserInterfaceVO>> listTopUserInvokeInterfaceInfo() {
        List<UserInterfaceInfo> userInterfaceInfoList = userInterfaceInfoMapper.listTopUserInvokeInterfaceInfo(5);
        Map<Long, List<UserInterfaceInfo>> userIdObjMap = userInterfaceInfoList.stream()
                .collect(Collectors.groupingBy(UserInterfaceInfo::getUserId));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", userIdObjMap.keySet());
        List<User> list = userService.list(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        //list为userId的信息
        //todo 只需返回用户信息即可
        List<UserInterfaceVO> interfaceInfoVOList = list.stream().map(user -> {
            UserInterfaceVO userVO = new UserInterfaceVO();
            BeanUtils.copyProperties(user, userVO);
            int totalNum = userIdObjMap.get(user.getId()).get(0).getTotalNum();
            userVO.setTotalNum(totalNum);
            return userVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(interfaceInfoVOList);
    }
}
