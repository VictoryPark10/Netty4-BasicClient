package com.vicsoft.client.init;

import com.vicsoft.client.Global;
import com.vicsoft.client.handler.BasicClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class BasicClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new IdleStateHandler(0, Global.WRITE_IDLE_SECONDS, 0, TimeUnit.SECONDS));
        pipeline.addLast(new BasicClientHandler());
    }

}
