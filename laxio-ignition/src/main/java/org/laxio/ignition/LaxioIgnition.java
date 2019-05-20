package org.laxio.ignition;

import org.laxio.api.exception.ignition.IgnitionStartupException;
import org.laxio.api.ignition.Ignitable;
import org.laxio.api.ignition.Ignition;
import org.laxio.ignition.scanner.IgnitionClassPathScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class LaxioIgnition implements Ignition {

    private static final Logger LOGGER = LoggerFactory.getLogger(LaxioIgnition.class);

    private final LaxioIgnitionArguments arguments;

    private final Set<Ignitable> ignitables;

    private File ignitablesDirectory;

    public LaxioIgnition(LaxioIgnitionArguments arguments) {
        this.arguments = arguments;
        this.ignitables = new HashSet<>();
        load();
    }

    private void load() {
        loadIgnitablesDirectory();
    }

    private void loadIgnitablesDirectory() {
        String name = arguments.getIgnitablesDirectoryName();

        File folder = new File(name);
        if (folder.exists()) {
            if (!folder.isDirectory()) {
                throw new IgnitionStartupException("Specified Ignitables directory '" + name + "' is not a directory");
            }
        } else {
            boolean mkdir = folder.mkdirs();
            if (!mkdir) {
                throw new IgnitionStartupException("Unable to create Ignitables directory");
            } else {
                LOGGER.info("Created new Ignitables directory");
            }
        }

        ignitablesDirectory = folder;
    }

    public void ignite() {
        if (arguments.isClassPathLoaded()) {
            IgnitionClassPathScanner.scan(LaxioIgnition.class.getClassLoader(), ignitable -> {
                LOGGER.info("Igniting '{}' v{}", ignitable.getDetails().getName(), ignitable.getDetails().getVersion());

                ignitable.ignite(this);
                ignitables.add(ignitable);
            });
        }
    }

    @Override
    public Set<Ignitable> getIgnitables() {
        return ignitables;
    }

    @Override
    public File getIgnitablesDirectory() {
        return ignitablesDirectory;
    }

}
