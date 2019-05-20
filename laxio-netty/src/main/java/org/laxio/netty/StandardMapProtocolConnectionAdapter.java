package org.laxio.netty;

import org.laxio.api.protocol.ProtocolAction;
import org.laxio.protocol.connection.MapProtocolConnectionAdapter;

import java.util.Map;

public class StandardMapProtocolConnectionAdapter extends MapProtocolConnectionAdapter {

    @Override
    protected void loadActions(Map<Class<?>, ProtocolAction<?>> mappedActions) {
        //
    }

}
