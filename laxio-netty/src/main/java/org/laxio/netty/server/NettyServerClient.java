package org.laxio.netty.server;

import org.laxio.api.protocol.connection.server.NetworkServer;
import org.laxio.api.protocol.connection.server.NetworkServerClient;
import org.laxio.api.server.ManagedServer;
import org.laxio.netty.ClientAdapter;

public class NettyServerClient extends ClientAdapter implements NetworkServerClient {

    private final NettyServer nettyServer;

    public NettyServerClient(NettyServer nettyServer) {
        this.nettyServer = nettyServer;
    }

    @Override
    public NetworkServer getNetworkServer() {
        return nettyServer;
    }

    @Override
    public ManagedServer getManagedServer() {
        return nettyServer.getManagedServer();
    }

}
