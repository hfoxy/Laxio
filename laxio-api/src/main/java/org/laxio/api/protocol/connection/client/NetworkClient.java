package org.laxio.api.protocol.connection.client;

import org.laxio.api.client.ManagedClient;
import org.laxio.api.protocol.connection.NetworkConnection;
import org.laxio.api.protocol.connection.NetworkEntity;
import org.laxio.api.protocol.connection.PlayerNetworkConnection;

public interface NetworkClient extends PlayerNetworkConnection, NetworkEntity {

    ManagedClient getManagedClient();

}
