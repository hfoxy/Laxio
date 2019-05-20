package org.laxio.server.ignition;

import org.laxio.api.ignition.BasicIgnitableDetails;
import org.laxio.api.ignition.Ignitable;
import org.laxio.api.ignition.IgnitableDetails;
import org.laxio.api.ignition.Ignition;
import org.laxio.api.protocol.Connection;
import org.laxio.api.protocol.Packet;
import org.laxio.api.protocol.ProtocolAction;
import org.laxio.protocol.action.KeepAliveAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaxioServerIgnition implements Ignitable {

    private static final Logger LOGGER = LoggerFactory.getLogger(LaxioServerIgnition.class);

    private static final IgnitableDetails DETAILS = new BasicIgnitableDetails("Laxio Server", "0.0.1");

    private IgnitableDetails details = DETAILS;

    @Override
    public IgnitableDetails getDetails() {
        return details;
    }

    @Override
    public void loadDetails(IgnitableDetails details) {
        this.details = details;
    }

    @Override
    public void ignite(Ignition ignition) {
        LOGGER.info("Server ignited!");

        Connection connection = new Connection() {
            @Override
            public void sendPacket(Packet packet) {
                LOGGER.info("whatever");
            }

            @Override
            public <T extends ProtocolAction<P>, P> void perform(Class<T> actionType, P params) {
                LOGGER.info("bork");
            }
        };

        connection.perform(KeepAliveAction.class, new KeepAliveAction.KeepAliveParams(System.currentTimeMillis()));
    }

}
