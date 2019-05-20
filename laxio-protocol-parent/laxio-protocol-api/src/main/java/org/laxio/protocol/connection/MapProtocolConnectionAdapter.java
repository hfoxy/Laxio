package org.laxio.protocol.connection;

import org.laxio.api.protocol.Connection;
import org.laxio.api.protocol.Packet;
import org.laxio.api.protocol.ProtocolAction;

import java.util.HashMap;
import java.util.Map;

public abstract class MapProtocolConnectionAdapter implements ProtocolConnectionAdapter {

    private final Map<Class<?>, ProtocolAction<?>> mappedActions;

    public MapProtocolConnectionAdapter() {
        this.mappedActions = new HashMap<>();
        loadActions(mappedActions);
    }

    protected abstract void loadActions(Map<Class<?>, ProtocolAction<?>> mappedActions);

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void sendPacket(Packet packet) {
        getConnection().sendPacket(packet);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends ProtocolAction<P>, P> void perform(Class<T> actionType, P params) {
        ProtocolAction<?> typelessAction = mappedActions.get(actionType);
        if (typelessAction == null) {
            throw new UnsupportedOperationException(actionType.getName() + " is not supported on this adapter");
        }

        ProtocolAction<P> action;
        try {
            action = (T) typelessAction;
        } catch (ClassCastException ex) {
            throw new UnsupportedOperationException("Can't cast action");
        }

        perform(action, params);
    }

}
