package org.laxio.netty.client.pipeline;

import io.netty.channel.socket.SocketChannel;
import org.laxio.netty.client.NettyClient;
import org.laxio.netty.client.NettyClientServer;
import org.laxio.netty.pipeline.NettyChannelInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyClientChannelInitializer extends NettyChannelInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyClientChannelInitializer.class);

    private final NettyClient nettyClient;

    public NettyClientChannelInitializer(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }

    @Override
    protected void registerInbound(SocketChannel channel) {
        super.registerInbound(channel);

        NettyClientServer server = new NettyClientServer(nettyClient);
        channel.pipeline().addLast("server", server);
    }

}
