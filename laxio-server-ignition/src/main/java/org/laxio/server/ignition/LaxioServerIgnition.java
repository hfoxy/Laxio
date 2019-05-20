package org.laxio.server.ignition;

import org.laxio.api.ignition.Ignitable;
import org.laxio.api.ignition.Ignition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaxioServerIgnition implements Ignitable {

    private static final Logger LOGGER = LoggerFactory.getLogger(LaxioServerIgnition.class);

    @Override
    public void ignite(Ignition ignition) {
        LOGGER.info("Server ignited!");
    }

}
