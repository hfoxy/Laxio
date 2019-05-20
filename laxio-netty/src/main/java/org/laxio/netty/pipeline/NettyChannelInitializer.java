package org.laxio.netty.pipeline;

import io.netty.channel.ChannelException;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyChannelInitializer.class);

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        try {
            channel.config().setOption(ChannelOption.TCP_NODELAY, true);
        } catch (ChannelException ex) {
            LOGGER.warn("Unable to set TCP_NODELAY", ex);
        }

        registerInbound(channel);

        registerOutbound(channel);
    }

    protected void registerInbound(SocketChannel channel) {
        // stage 1: input - timeout
        // disconnect after 30 secs of no io
        channel.pipeline().addLast("timeout", new ReadTimeoutHandler(30));
    }

    protected void registerOutbound(SocketChannel channel) {
        //
    }

}
