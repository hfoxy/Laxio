package org.laxio.ignition;

import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaxioIgnitionStart {

    private static final Logger LOGGER = LoggerFactory.getLogger(LaxioIgnitionStart.class);

    public static void main(String[] args) throws ParseException {
        LOGGER.debug("Laxio Ignited with args: {}", new Object[]{args});

        LaxioIgnitionArguments arguments = new LaxioIgnitionArguments(args);
        if (arguments.isHelpRequested()) {
            arguments.showHelp();
            return;
        }

        LaxioIgnition ignition = new LaxioIgnition(arguments);
        ignition.ignite();
    }

}
