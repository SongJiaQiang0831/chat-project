package org.zz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author songjiaqiang  2023/11/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private long msgId;
    private long senderId;
    private long receiveId;

    private String subject;

    private String content;
    private Date sendTime;

    private MsgState msgState;
    enum MsgState{

    }
}
