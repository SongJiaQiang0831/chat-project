package org.zz.chatcore;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author songjiaqiang  2023/11/24
 */
public class SessionManager {
    private static Map<String, HttpSession> sessionMap =new HashMap<>();
    public static void put(String sessionId,HttpSession user){
        sessionMap.put(sessionId,user);
    }
    public static boolean containUser(String sessionId){
        return sessionMap.containsKey(sessionId);
    }
    public static HttpSession getCurUser(String sessionId){
        return sessionMap.get(sessionId);
    }
}
