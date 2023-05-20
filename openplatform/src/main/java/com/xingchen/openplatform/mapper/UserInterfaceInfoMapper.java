package com.xingchen.openplatform.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xingchen.common.model.entity.UserInterfaceInfo;

import java.util.List;


/**
* @author xing'chen
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Mapper
* @createDate 2022-11-26 15:15:32
* @Entity generator.domain.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {
    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);
}




