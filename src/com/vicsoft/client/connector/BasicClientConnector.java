package com.vicsoft.client.connector;

import com.vicsoft.client.Global;
import com.vicsoft.client.init.BasicClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class BasicClientConnector {

    public static BasicClientConnector getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final BasicClientConnector INSTANCE = new BasicClientConnector();
    }

    public void connect() {
        log.info("Try connect to server..");

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(new DefaultThreadFactory("BasicClient"));

        Bootstrap bootstrap = new Bootstrap().group(eventLoopGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.remoteAddress(new InetSocketAddress(Global.SERVER_IP, Global.SERVER_PORT));
        bootstrap.handler(new BasicClientInitializer());

        ChannelFuture channelFuture = bootstrap.connect();
        channelFuture.addListener((ChannelFuture future) -> {
            if (future.isSuccess()) {
                log.info("Connect success");
            } else {
                log.warn("Connect fail", future.cause());
                future.channel().close();
            }
        });
    }

}
