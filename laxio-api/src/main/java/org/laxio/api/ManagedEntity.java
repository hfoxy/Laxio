package org.laxio.api;

public interface ManagedEntity {

    void start();

    boolean isRunning();

    void stop();

}
