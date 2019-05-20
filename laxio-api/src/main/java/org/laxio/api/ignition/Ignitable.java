package org.laxio.api.ignition;

public interface Ignitable {

    IgnitableDetails getDetails();

    void loadDetails(IgnitableDetails details);

    void ignite(Ignition ignition);

}
