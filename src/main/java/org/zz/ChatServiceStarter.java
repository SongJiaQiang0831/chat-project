package org.zz;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zz.chatcore.NettyServer;

/**
 * @author songjiaqiang  2023/11/13
 */
@SpringBootApplication
public class ChatServiceStarter implements InitializingBean {
    public static void main(String[] args){
      SpringApplication.run(ChatServiceStarter.class,args);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        NettyServer.inst().start(8888);
    }
}
