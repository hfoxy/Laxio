package org.laxio.api.client;

import org.laxio.api.ManagedEntity;
import org.laxio.api.protocol.connection.client.NetworkClient;

public interface ManagedClient extends Client, ManagedEntity {

    @Override
    NetworkClient getNetworkEntity();

}
