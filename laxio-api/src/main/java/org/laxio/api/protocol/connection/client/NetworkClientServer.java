package org.laxio.api.protocol.connection.client;

import org.laxio.api.client.ManagedClient;
import org.laxio.api.protocol.connection.NetworkConnection;
import org.laxio.api.protocol.connection.NetworkEntity;
import org.laxio.api.server.Server;

public interface NetworkClientServer extends NetworkConnection, NetworkEntity {

    NetworkClient getNetworkClient();

    ManagedClient getManagedClient();

    Server getServer();

}
