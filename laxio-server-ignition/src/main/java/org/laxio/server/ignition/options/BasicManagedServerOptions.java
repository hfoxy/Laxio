package org.laxio.server.ignition.options;

import org.laxio.api.server.ManagedServerOptions;

public class BasicManagedServerOptions implements ManagedServerOptions {

    private final String host;
    private final int port;

    public BasicManagedServerOptions(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }

}
