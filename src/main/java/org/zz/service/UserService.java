package org.zz.service;

import org.springframework.stereotype.Service;
import org.zz.chatcore.SessionManager;
import org.zz.pojo.User;

import javax.servlet.http.HttpSession;

/**
 * @author songjiaqiang  2023/11/24
 */
@Service
public class UserService {

    public User getCurrentUser(){
        return null;
    }
    public void login(HttpSession session, String userName, String password){
        if(!SessionManager.containUser(session.getId())){
            //用户已经登录;
            return ;
        }
        SessionManager.put(session.getId(),session);
    }

    public void exit(){

    }

}
