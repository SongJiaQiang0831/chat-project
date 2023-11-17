package org.zz.pojovo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

/**
 * @author songjiaqiang  2023/11/16
 */
@Data
@NoArgsConstructor
public class MessageVo {
    private Long id;

    private long senderId;

    /**
     * 主题
     */
    private String subject;
    /**
     * 内容
     */
    private String content;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp sendTime;
    /**
     * 接收消息时间，读取时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp readTime;

    public MessageVo(Long id, long senderId, String subject, String content, Timestamp sendTime, Timestamp readTime) {
        this.id = id;
        this.senderId = senderId;
        this.subject = subject;
        this.content = content;
        this.sendTime = sendTime;
        this.readTime = readTime;
    }

    public MessageVo(Long id, long senderId, String subject, String content) {
        this.id = id;
        this.senderId = senderId;
        this.subject = subject;
        this.content = content;
    }
}
