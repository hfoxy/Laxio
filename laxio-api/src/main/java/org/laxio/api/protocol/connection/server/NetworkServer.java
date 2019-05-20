package org.laxio.api.protocol.connection.server;

import org.laxio.api.client.Client;
import org.laxio.api.protocol.connection.NetworkEntity;
import org.laxio.api.server.ManagedServer;

import java.util.Set;

public interface NetworkServer extends NetworkEntity {

    ManagedServer getManagedServer();

    Set<Client> getClients();

    void start();

    void stop();

}
