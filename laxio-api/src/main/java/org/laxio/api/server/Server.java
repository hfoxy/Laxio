package org.laxio.api.server;

import org.laxio.api.ignition.Ignited;
import org.laxio.api.protocol.connection.NetworkEntity;

public interface Server extends Ignited {

    NetworkEntity getNetworkEntity();

}
