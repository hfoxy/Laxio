package org.laxio.api.ignition;

import java.io.File;
import java.util.Set;

public interface Ignition {

    Set<Ignitable> getIgnitables();

    File getIgnitablesDirectory();

}
