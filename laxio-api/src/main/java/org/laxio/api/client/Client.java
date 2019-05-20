package org.laxio.api.client;

import org.laxio.api.ignition.Ignited;
import org.laxio.api.protocol.connection.NetworkEntity;

public interface Client extends Ignited {

    NetworkEntity getNetworkEntity();

}
