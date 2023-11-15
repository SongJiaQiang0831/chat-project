package org.zz.chatcore;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zz.pojo.Group;
import org.zz.pojo.Message;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author songjiaqiang  2023/11/13
 */
@ChannelHandler.Sharable
public class NettyHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static final Logger LOG = LoggerFactory.getLogger(NettyHandler.class);

    /**
     * 存储用户对应的通道，便于推送给用户 。  用户id -> channel
     */
    public static Map<Long, ChannelHandlerContext> USER_2_CHANNEL = new ConcurrentHashMap<>(16);

    /**
     * 存放通道对应的用户 。  channel id -> 用户id
     */
    public static Map<String,Long> CHANNEL_2_USER = new ConcurrentHashMap<>(16);

    /**
     * 存储当前连接上的通道，可以理解为用户的集合 。
     */
    public static List<ChannelHandlerContext> LIST = new CopyOnWriteArrayList<>();

    /**
     * 群组消息功能   群id -> channell
     */
    public static Map<Long,List<ChannelHandlerContext>> GROUP_2_CHANNELS =new ConcurrentHashMap<>();
    /**
     * 群里都有哪些用户 ; 群id -> users
     */
    public static Map<Long,List<Long>>  GROUP_2_USERS=new ConcurrentHashMap<>();

    /**
     * 用户 所在的群 ;    用户id -> 群ids
     */
    public static Map<Long,List<Long>>  USER_2_GROUPS=new ConcurrentHashMap<>();

    /**
     * 通道连接事件
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LIST.add(ctx);
        LOG.info("有新的连接进入当前连接数量:"+LIST.size());
        //读取三条ES消息 推送给自己就行了;
    }

    /**
     * 通道消息事件
     * @param ctx
     * @param textWebSocketFrame
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {
        //第一次发消息需要 初始化 上面的map
        String text = textWebSocketFrame.text();
        Message msg = null;
        try {
            msg = JSON.parseObject(text, Message.class);
        }catch (Exception e){
            //序列化失败可能是建群操作;
            Group group = JSON.parseObject(text, Group.class);
            processInitGroup(group,ctx);
            return ;
        }

        if(Objects.isNull(msg)){
            LOG.warn("消息体为null");
            return ;
        }
        //建群,待思考
       /* if(msg.isCreateGroup()){
            processInitGroup(msg,ctx);
            return ;
        }*/
        //双向绑定
        processInitMap(msg,ctx);

        LOG.info("前端请求:"+textWebSocketFrame.text());
    }
    private void processInitMap(Message message,ChannelHandlerContext ctx){
        Long senderId = message.getSenderId();
        if(USER_2_CHANNEL.containsKey(senderId)) return ;
        //双向绑定
        USER_2_CHANNEL.put(senderId,ctx);
        CHANNEL_2_USER.put(ctx.channel().id().toString(),senderId);
    }
    private void processInitGroup(Group group,ChannelHandlerContext ctx){
        //建群需要保证都在线;(后期可以考虑加入 mq)
        long sponsor = group.getSponsor();
        List<Long> receiveIds = group.getReceiveIds();
        long groupId=System.currentTimeMillis();
        List<ChannelHandlerContext> ctxs= Lists.newArrayList();
        for (int i = 0; i < receiveIds.size(); ++i) {
            Long userId = receiveIds.get(i);
            if(USER_2_CHANNEL.containsKey(userId)){  //与需要建立的用户们
                ctxs.add(USER_2_CHANNEL.get(userId));
            }
        }
        if(CollectionUtils.isNotEmpty(ctxs)){
            GROUP_2_CHANNELS.put(groupId,ctxs);
            GROUP_2_USERS.put(groupId,receiveIds);
            if (USER_2_GROUPS.containsKey(sponsor)) {
                USER_2_GROUPS.get(sponsor).add(groupId);
            }else{
                List<Long> groupIds = Lists.newArrayList();
                groupIds.add(groupId);
                USER_2_GROUPS.put(sponsor,groupIds);
            }

        }

    }

    /**
     * 通达关闭事件
     * @param ctx
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
       /* 释放资源操作 ，暂时不做处理；
        String s = CHANNEL_USER.get(ctx.channel().id().toString());
        MAP.remove(s);
        // 给其他在线用户发送该用户离线的信息
        for (ChannelHandlerContext handlerContext : MAP.values()) {
            Message message = new Message("下线通知","");
            handlerContext.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
        }
        LIST.remove(ctx);
        CHANNEL_USER.remove(ctx.channel().id().toString());*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       /* String s = CHANNEL_USER.get(ctx.channel().id().toString());
        MAP.remove(s);
        // 给其他在线用户发送该用户离线的信息
        for (ChannelHandlerContext handlerContext : MAP.values()) {
//            Message message = new Message("服务端",null, UUID.randomUUID().toString(),"用户--"+s+"--连接发生问题，已被迫离线了",2);
//            handlerContext.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
        }
        LIST.remove(ctx);
        CHANNEL_USER.remove(ctx.channel().id().toString());*/
    }
}

