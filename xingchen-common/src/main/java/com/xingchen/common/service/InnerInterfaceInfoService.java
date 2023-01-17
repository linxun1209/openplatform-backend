package com.xingchen.common.service;

import com.xingchen.common.model.entity.InterfaceInfo;

/**
 *
 * @author xing'chen
 */
public interface InnerInterfaceInfoService {

    /**
     * 从数据库中查询模拟接口是否存在（请求路径、请求方法、请求参数）
     */
    InterfaceInfo getInterfaceInfo(String path, String method);
}
