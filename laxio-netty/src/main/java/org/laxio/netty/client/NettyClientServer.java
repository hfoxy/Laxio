package org.laxio.netty.client;

import org.laxio.api.client.ManagedClient;
import org.laxio.api.protocol.connection.client.NetworkClient;
import org.laxio.api.protocol.connection.client.NetworkClientServer;
import org.laxio.api.server.Server;
import org.laxio.netty.ClientAdapter;

public class NettyClientServer extends ClientAdapter implements NetworkClientServer {

    private final NettyClient nettyClient;

    private Server server;

    public NettyClientServer(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }

    @Override
    public ManagedClient getManagedClient() {
        return nettyClient.getManagedClient();
    }

    @Override
    public NetworkClient getNetworkClient() {
        return nettyClient;
    }

    @Override
    public Server getServer() {
        return server;
    }

}
