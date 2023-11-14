package org.zz.chatcore;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author songjiaqiang  2023/11/13
 */
public class NettyServer implements DisposableBean {
    private static final Logger LOG = LoggerFactory.getLogger(NettyServer.class);
    /**
     * 单例
     */
    private static NettyServer singleInstance = new NettyServer();
    public static NettyServer inst()
    {
        return singleInstance;
    }
    /**
     * 通道初始化对象
     */

    private WebSocketChannel webSocketChannelInit=new WebSocketChannel();

    /**
     * boos线程组
     */
    private EventLoopGroup boos;

    /**
     * work线程组
     */
    private EventLoopGroup work;

    /**
     * 自定义启动方法
     * @param port
     */
    public void start(int port) {
        // 设置boos线程组
        boos = new NioEventLoopGroup(1);
        // 设置work线程组
        work = new NioEventLoopGroup();
        // 创建启动助手
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boos,work)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler())
                .childHandler(webSocketChannelInit);
        // 绑定ip和端口启动服务端
        ChannelFuture sync = null;
        try {
            // 绑定netty的启动端口
            sync = serverBootstrap.bind(port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            close();
        }
        try {
            LOG.info("netty服务器启动成功,监听远程IP：{} --端口:{}", InetAddress.getLocalHost(),port);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        };
        sync.channel().closeFuture();
    }

    /**
     * 容器销毁前关闭线程组
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        close();
    }

    /**
     * 关闭方法
     */
    public void close() {
        if (boos!=null) {
            boos.shutdownGracefully();
        }
        if (work!=null) {
            work.shutdownGracefully();
        }
    }
}
