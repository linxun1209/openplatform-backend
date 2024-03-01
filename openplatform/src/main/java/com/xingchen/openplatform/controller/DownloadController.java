package com.xingchen.openplatform.controller;

import com.xingchen.common.model.entity.User;
import com.xingchen.openplatform.common.ErrorCode;
import com.xingchen.openplatform.exception.BusinessException;
import com.xingchen.openplatform.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@RestController
@Slf4j
@RequestMapping("download")
public class DownloadController {
    @Autowired
    private UserService userService;
    // todo 文件下载在服务器路径，因为我这只有一个文件，所以这么写，实际可以根据访问路径来
    private static final String SDK_HELP_ADDRESS = "/home/XC/download/xcapi-cl";
    @GetMapping("sdkHelp")
    public ResponseEntity<Resource> downloadSdkHelp(HttpServletRequest request) throws IOException{
        // 请求不能为null//


    if (request == null) {
        throw new BusinessException(ErrorCode.FORBIDDEN_ERROR);
    }
    // 当前登录用户要存在
    User loginUser = userService.getLoginUser(request);
 if (loginUser == null || loginUser.getId() == null) {
        throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
    }
    //获取要下载的文件
    File file = new File(SDK_HELP_ADDRESS);
    InputStream is = Files.newInputStream(file.toPath());
    //获取文件输入流
    //创建资源对象,将输入流加载其中
    InputStreamResource resource = new InputStreamResource(is);
    //设置响应头,指定文件名称
    HttpHeaders headers = new HttpHeaders();
    // 只有设置正确的响应头，客户端才知道此时是文件下载
    headers.add("Content-Disposition", String.format("attachment; filename=%s",file.getName()));
         //返回文件下载响应
    return ResponseEntity.ok().headers(headers).contentLength(file.length()).body(resource);
}
}
