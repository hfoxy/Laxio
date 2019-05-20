package org.laxio.netty.client;

import org.laxio.api.client.ManagedClient;
import org.laxio.api.protocol.Packet;
import org.laxio.api.protocol.ProtocolAction;
import org.laxio.api.protocol.connection.client.NetworkClient;

public class NettyClient implements NetworkClient {

    private final ManagedClient managedClient;

    private NettyClientServer nettyClientServer;

    public NettyClient(ManagedClient managedClient) {
        this.managedClient = managedClient;
    }

    @Override
    public ManagedClient getManagedClient() {
        return managedClient;
    }

    @Override
    public void sendPacket(Packet packet) {
        nettyClientServer.sendPacket(packet);
    }

    @Override
    public <T extends ProtocolAction<P>, P> void perform(Class<T> actionType, P params) {
        nettyClientServer.perform(actionType, params);
    }

    @Override
    public <P> void perform(ProtocolAction<P> action, P params) {
        nettyClientServer.perform(action, params);
    }

}
