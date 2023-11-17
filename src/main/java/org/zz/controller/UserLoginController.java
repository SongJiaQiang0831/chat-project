package org.zz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author songjiaqiang  2023/11/17
 */
@RestController
@RequestMapping("/login")
public class UserLoginController {

    @GetMapping("/login")
    public String login(@RequestParam("id")Long id,@RequestParam("name")String name){

    }
}
