package org.zz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zz.pojo.Result;
import org.zz.pojovo.UserVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songjiaqiang  2023/11/13
 */
@RestController
@RequestMapping("/api/manager/")
public class ManagerController {

    //1、查询所有用户；
    //2、查询所有在线用户;
    @GetMapping("allUser")
    public Result<List<UserVo>> allUser(){
        return Result.success(new ArrayList<>());
    }
    @GetMapping("allIOnlineUser")
    public Result<List<UserVo>> allIOnlineUser(){
        return Result.success(new ArrayList<>());
    }


}
