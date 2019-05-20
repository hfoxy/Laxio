package org.laxio.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.laxio.api.protocol.Packet;
import org.laxio.api.protocol.ProtocolAction;
import org.laxio.api.protocol.connection.NetworkConnection;
import org.laxio.protocol.connection.ProtocolConnectionAdapter;

public class ClientAdapter extends ChannelInboundHandlerAdapter implements NetworkConnection {

    private ProtocolConnectionAdapter connectionAdapter;

    public ClientAdapter() {
        connectionAdapter = new StandardMapProtocolConnectionAdapter();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);

        //
    }

    @Override
    public void sendPacket(Packet packet) {
        connectionAdapter.sendPacket(packet);
    }

    @Override
    public <T extends ProtocolAction<P>, P> void perform(Class<T> actionType, P params) {
        connectionAdapter.perform(actionType, params);
    }

    @Override
    public <P> void perform(ProtocolAction<P> action, P params) {
        connectionAdapter.perform(action, params);
    }

}
