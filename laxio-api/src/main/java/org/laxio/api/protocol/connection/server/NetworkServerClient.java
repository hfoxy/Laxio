package org.laxio.api.protocol.connection.server;

import org.laxio.api.protocol.connection.NetworkEntity;
import org.laxio.api.protocol.connection.PlayerNetworkConnection;
import org.laxio.api.server.ManagedServer;

public interface NetworkServerClient extends PlayerNetworkConnection, NetworkEntity {

    NetworkServer getNetworkServer();

    ManagedServer getManagedServer();

}
