package org.laxio.protocol.connection;

import org.laxio.api.protocol.Connection;
import org.laxio.api.protocol.Packet;
import org.laxio.api.protocol.ProtocolAction;

public interface ProtocolConnectionAdapter {

    Connection getConnection();

    default void sendPacket(Packet packet) {
        getConnection().sendPacket(packet);
    }

    <T extends ProtocolAction<P>, P> void perform(Class<T> actionType, P params);

    default <P> void perform(ProtocolAction<P> action, P params) {
        action.send(getConnection(), params);
    }

}
