package com.xingchen.openplatform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.xingchen.common.model.entity.InterfaceInfo;
import com.xingchen.common.model.entity.User;
import com.xingchen.openplatform.annotation.AuthCheck;
import com.xingchen.openplatform.common.*;
import com.xingchen.openplatform.constant.CommonConstant;
import com.xingchen.openplatform.exception.BusinessException;
import com.xingchen.openplatform.model.dto.interfaceinfo.InterfaceInfoAddRequest;
import com.xingchen.openplatform.model.dto.interfaceinfo.InterfaceInfoInvokeRequest;
import com.xingchen.openplatform.model.dto.interfaceinfo.InterfaceInfoQueryRequest;
import com.xingchen.openplatform.model.dto.interfaceinfo.InterfaceInfoUpdateRequest;
import com.xingchen.openplatform.model.enums.InterfaceInfoStatusEnum;
import com.xingchen.openplatform.service.InterfaceInfoService;
import com.xingchen.openplatform.service.UserService;
import com.xingchen.sdkxingchen.clinet.ClientXingchen;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 帖子接口
 *
 * @author yupi
 */
@RestController
@RequestMapping("/InterfaceInfo")
@Slf4j
public class InterfaceInfoController {

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Resource
    private UserService userService;


    @Resource
    private ClientXingchen clientXingchen;

    // region 增删改查

    /**
     * 创建
     *
     * @param interfaceInfoAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addInterfaceInfo(@RequestBody InterfaceInfoAddRequest interfaceInfoAddRequest, HttpServletRequest request) {
        if (interfaceInfoAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoAddRequest, interfaceInfo);
        // 校验
        interfaceInfoService.validInterfaceInfo(interfaceInfo, true);
        User loginUser = userService.getLoginUser(request);
        interfaceInfo.setUserId(loginUser.getId());
        boolean result = interfaceInfoService.save(interfaceInfo);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        long newInterfaceInfoId = interfaceInfo.getId();
        return ResultUtils.success(newInterfaceInfoId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteInterfaceInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可删除
        if (!oldInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = interfaceInfoService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新
     *
     * @param interfaceInfoUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateInterfaceInfo(@RequestBody InterfaceInfoUpdateRequest interfaceInfoUpdateRequest,
                                                     HttpServletRequest request) {
        if (interfaceInfoUpdateRequest == null || interfaceInfoUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoUpdateRequest, interfaceInfo);
        // 参数校验
        interfaceInfoService.validInterfaceInfo(interfaceInfo, false);
        User user = userService.getLoginUser(request);
        long id = interfaceInfoUpdateRequest.getId();
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可修改
        if (!oldInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = interfaceInfoService.updateById(interfaceInfo);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<InterfaceInfo> getInterfaceInfoById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(id);
        return ResultUtils.success(interfaceInfo);
    }

    /**
     * 获取列表（仅管理员可使用）
     *
     * @param interfaceInfoQueryRequest
     * @return
     */
    @AuthCheck(mustRole = "admin")
    @GetMapping("/list")
    public BaseResponse<List<InterfaceInfo>> listInterfaceInfo(InterfaceInfoQueryRequest interfaceInfoQueryRequest) {
        InterfaceInfo interfaceInfoQuery = new InterfaceInfo();
        if (interfaceInfoQueryRequest != null) {
            BeanUtils.copyProperties(interfaceInfoQueryRequest, interfaceInfoQuery);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>(interfaceInfoQuery);
        List<InterfaceInfo> interfaceInfoList = interfaceInfoService.list(queryWrapper);
        return ResultUtils.success(interfaceInfoList);
    }

    /**
     * 分页获取列表
     *
     * @param interfaceInfoQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<InterfaceInfo>> listInterfaceInfoByPage(InterfaceInfoQueryRequest interfaceInfoQueryRequest, HttpServletRequest request) {
        if (interfaceInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfoQuery = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoQueryRequest, interfaceInfoQuery);
        long current = interfaceInfoQueryRequest.getCurrent();
        long size = interfaceInfoQueryRequest.getPageSize();
        String sortField = interfaceInfoQueryRequest.getSortField();
        String sortOrder = interfaceInfoQueryRequest.getSortOrder();
        String description = interfaceInfoQuery.getDescription();
        // description 需支持模糊搜索
        interfaceInfoQuery.setDescription(null);
        // 限制爬虫
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>(interfaceInfoQuery);
        queryWrapper.like(StringUtils.isNotBlank(description), "description", description);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<InterfaceInfo> interfaceInfoPage = interfaceInfoService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(interfaceInfoPage);
    }


    /**
     * 发布
     *
     * @param idRequest
     * @param request
     * @return
     */
    @PostMapping("/online")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<Boolean> onlineInterfaceInfo(@RequestBody IdRequest idRequest,
                                                     HttpServletRequest request) {
        if (idRequest == null || idRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long id = idRequest.getId();
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 判断该接口是否可以调用
        com.xingchen.sdkxingchen.model.User user = new com.xingchen.sdkxingchen.model.User();
        user.setUsername("test");
//        String username = clientXingchen.getUsernameByPost(user);
//        if (StringUtils.isBlank(username)) {
//            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "接口验证失败");
//        }
        // 仅本人或管理员可修改
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        interfaceInfo.setId(id);
        interfaceInfo.setStatus(InterfaceInfoStatusEnum.ONLINE.getValue());
        boolean result = interfaceInfoService.updateById(interfaceInfo);
        return ResultUtils.success(result);
    }

    /**
     * 下线
     *
     * @param idRequest
     * @param request
     * @return
     */
    @PostMapping("/offline")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<Boolean> offlineInterfaceInfo(@RequestBody IdRequest idRequest,
                                                      HttpServletRequest request) {
        if (idRequest == null || idRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long id = idRequest.getId();
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可修改
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        interfaceInfo.setId(id);
        interfaceInfo.setStatus(InterfaceInfoStatusEnum.OFFLINE.getValue());
        boolean result = interfaceInfoService.updateById(interfaceInfo);
        return ResultUtils.success(result);
    }


    /**
     * 测试调用
     *
     * @param interfaceInfoInvokeRequest
     * @param request
     * @return
     */
    @PostMapping("/invoke")
    public BaseResponse<Object> invokeInterfaceInfo(@RequestBody InterfaceInfoInvokeRequest interfaceInfoInvokeRequest,
                                                    HttpServletRequest request) {
        if (interfaceInfoInvokeRequest == null || interfaceInfoInvokeRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long id = interfaceInfoInvokeRequest.getId();
        String userRequestParams = interfaceInfoInvokeRequest.getUserRequestParams();
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        //数据库中找到请求方法
        String interfaceInfoName = oldInterfaceInfo.getName();
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        if (oldInterfaceInfo.getStatus() == InterfaceInfoStatusEnum.OFFLINE.getValue()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口已关闭");
        }
        User loginUser = userService.getLoginUser(request);
        String accessKey = loginUser.getAccessKey();
        String secretKey = loginUser.getSecretKey();
        Object result = reflectionInterface(ClientXingchen.class, interfaceInfoName, userRequestParams, accessKey, secretKey);
        //网关拦截对异常处理
        if (result.equals(GateWayErrorCode.FORBIDDEN.getCode())){
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR,"调用次数已用尽");
        }
        return ResultUtils.success(result);
    }


    /**
     * 利用反射来进行对多个接口调用
     * @param reflectionClass
     * @param methodName
     * @param parameter
     * @param accessKey
     * @param secretKey
     * @return
     */
    public Object reflectionInterface(Class<?> reflectionClass, String methodName, String parameter, String accessKey, String secretKey) {
        //构造反射类的实例
        Object result = null;
        try {
            Constructor<?> constructor = reflectionClass.getDeclaredConstructor(String.class, String.class);
            //获取SDK的实例，同时传入密钥
            ClientXingchen geapiClient = (ClientXingchen) constructor.newInstance(accessKey, secretKey);
            //获取SDK中所有的方法
            Method[] methods = geapiClient.getClass().getMethods();
            //筛选出调用方法
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    //获取方法参数类型
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    Method method1;
                    if (parameterTypes.length == 0){
                        method1 = geapiClient.getClass().getMethod(methodName);
                        return method1.invoke(geapiClient);
                    }
                    method1 = geapiClient.getClass().getMethod(methodName, parameterTypes[0]);
                    //getMethod，多参会考虑重载情况获取方法,前端传来参数是JSON格式转换为String类型
                    //参数Josn化
                    Gson gson = new Gson();
                    Object args = gson.fromJson(parameter, parameterTypes[0]);
                    return result = method1.invoke(geapiClient, args);
                }
            }
        } catch (Exception e) {
            log.error("反射调用参数错误",e);
        }
        return result;
    }
}