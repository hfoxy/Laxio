package org.laxio.server;

import org.laxio.api.ignition.Ignition;
import org.laxio.api.protocol.connection.server.NetworkServer;
import org.laxio.api.server.ManagedServer;
import org.laxio.api.server.ManagedServerOptions;
import org.laxio.netty.server.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaxioManagedServer implements ManagedServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(LaxioManagedServer.class);

    private final Ignition ignition;
    private final ManagedServerOptions options;

    private NetworkServer networkServer;

    public LaxioManagedServer(Ignition ignition, ManagedServerOptions options) {
        this.ignition = ignition;
        this.options = options;
    }

    @Override
    public Ignition getIgnition() {
        return ignition;
    }

    @Override
    public NetworkServer getNetworkEntity() {
        return networkServer;
    }

    @Override
    public ManagedServerOptions getOptions() {
        return options;
    }

    @Override
    public void start() {
        LOGGER.info("Starting server");
        networkServer = new NettyServer(this);
        networkServer.start();
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void stop() {
        LOGGER.info("Stopping server");
    }

}
