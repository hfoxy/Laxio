package org.laxio.netty.server.pipeline;

import io.netty.channel.socket.SocketChannel;
import org.laxio.netty.pipeline.NettyChannelInitializer;
import org.laxio.netty.server.NettyServer;
import org.laxio.netty.server.NettyServerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServerChannelInitializer extends NettyChannelInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServerChannelInitializer.class);

    private final NettyServer nettyServer;

    public NettyServerChannelInitializer(NettyServer nettyServer) {
        this.nettyServer = nettyServer;
    }

    @Override
    protected void registerInbound(SocketChannel channel) {
        super.registerInbound(channel);

        NettyServerClient client = new NettyServerClient(nettyServer);
        channel.pipeline().addLast("client", client);
    }

}
