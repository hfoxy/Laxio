package org.laxio.api.ignition;

public class BasicIgnitableDetails implements IgnitableDetails {

    private final String name;
    private final String version;

    public BasicIgnitableDetails(String name, String version) {
        this.name = name;
        this.version = version;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVersion() {
        return version;
    }

}
