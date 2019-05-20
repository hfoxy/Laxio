package org.laxio.api.server;

import org.laxio.api.ManagedEntity;
import org.laxio.api.protocol.connection.server.NetworkServer;

public interface ManagedServer extends Server, ManagedEntity {

    @Override
    NetworkServer getNetworkEntity();

    ManagedServerOptions getOptions();

}
