package org.laxio.api.protocol;

public interface ProtocolAction<P> {

    void send(Connection connection, P params);

}
