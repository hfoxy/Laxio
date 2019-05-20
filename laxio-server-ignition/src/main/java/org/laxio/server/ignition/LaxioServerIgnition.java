package org.laxio.server.ignition;

import org.laxio.api.ignition.BasicIgnitableDetails;
import org.laxio.api.ignition.Ignitable;
import org.laxio.api.ignition.IgnitableDetails;
import org.laxio.api.ignition.Ignition;
import org.laxio.server.LaxioManagedServer;
import org.laxio.server.ignition.options.BasicManagedServerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaxioServerIgnition implements Ignitable {

    private static final Logger LOGGER = LoggerFactory.getLogger(LaxioServerIgnition.class);

    private static final IgnitableDetails DETAILS = new BasicIgnitableDetails("Laxio Server", "0.0.1");

    private IgnitableDetails details = DETAILS;

    @Override
    public IgnitableDetails getDetails() {
        return details;
    }

    @Override
    public void loadDetails(IgnitableDetails details) {
        this.details = details;
    }

    @Override
    public void ignite(Ignition ignition) {
        LOGGER.info("Server ignited!");

        LaxioManagedServer server = new LaxioManagedServer(ignition, new BasicManagedServerOptions("0.0.0.0", 25565));

        server.start();
    }

}
