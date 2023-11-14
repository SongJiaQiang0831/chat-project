package org.zz.chatcore;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author songjiaqiang  2023/11/13
 */
public class NettyHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static final Logger LOG = LoggerFactory.getLogger(NettyHandler.class);

    /**
     * 存储用户对应的通道，便于推送给用户 。
     */
    public static Map<String, ChannelHandlerContext> MAP = new ConcurrentHashMap<>(16);

    /**
     * 存放通道对应的用户 。
     */
    public static Map<String,String> CHANNEL_USER = new ConcurrentHashMap<>(16);

    /**
     * 存储当前连接上的通道，可以理解为用户的集合 。
     */
    public static List<ChannelHandlerContext> LIST = new CopyOnWriteArrayList<>();

    /**
     * 通道连接事件
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        LIST.add(ctx);
//        MAP.put(authUtil.getCurrentUserAccount(),ctx);
        LOG.info("有新的连接进入当前连接数量:"+MAP.size());
        //读取三条ES消息 推送给自己就行了;
    }

    /**
     * 通道消息事件
     * @param channelHandlerContext
     * @param textWebSocketFrame
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        LOG.info("前端请求:"+textWebSocketFrame.text());
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

