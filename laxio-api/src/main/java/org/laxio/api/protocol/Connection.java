package org.laxio.api.protocol;

public interface Connection {

    void sendPacket(Packet packet);

    <T extends ProtocolAction<P>, P> void perform(Class<T> actionType, P params);

    default <P> void perform(ProtocolAction<P> action, P params) {
        action.send(this, params);
    }

}
