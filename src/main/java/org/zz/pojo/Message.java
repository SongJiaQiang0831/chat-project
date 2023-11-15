package org.zz.pojo;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

/**
 * @author songjiaqiang  2023/11/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    /**
     * 消息id
     */
    private long msgId;
    /**
     * 发送人id
     */
    private long senderId;
    /**
     * 接收人id
     */
    private Long receiveId;
    /**
     * 主题
     */
    private String subject;
    /**
     * 内容
     */
    private String content;
    /**
     * 发送消息时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp sendTime;
    /**
     * 接收消息时间，读取时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp readTime;
    /**
     * 消息状态;默认未读
     */
    private MsgState msgState=MsgState.UNREAD;

    /**
     * 建群请求;
     */
    private boolean isCreateGroup=false;
    /**
     * 消息发送到哪个群;
     */
    private Long groupId;
    @AllArgsConstructor
    @NoArgsConstructor
    enum MsgState{
        READ(0,"已读"),
        UNREAD(1,"未读");
        int flag=0;
        String msgState="";
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
