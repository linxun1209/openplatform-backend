package com.xingchen.apiinterface.controller;


import com.xingchen.sdkxingchen.model.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author xingchen
 * @version V1.0
 * @Package com.xingchen.apiinterface.controller
 * @date 2022/11/7 12:51
 */
@RestController()
@RequestMapping("/api")
public class UserController {



    @GetMapping()
    public String getUsername(@RequestParam String username){
        return "GET的"+username;
    }

    @PostMapping("user")
    public String User(@RequestParam String user){
        return "post"+user;
    }

    @PostMapping()
    public String PostUser(@RequestParam User user){
        return "该用户的名字是"+user;
    }
}
