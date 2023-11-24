package org.zz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zz.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * @author songjiaqiang  2023/11/17
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class UserLoginController {
    @Autowired
    UserService userService;
    @GetMapping("/login")
    public String login(HttpSession session, @RequestParam("userName")String userName, @RequestParam("passWord")String passWord){
        userService.login(session,userName,passWord);
        return "success;";
    }
    @GetMapping("/login2")
    public String login2(){
        log.debug("dsadasdasda");
        return "success;";
    }
}
