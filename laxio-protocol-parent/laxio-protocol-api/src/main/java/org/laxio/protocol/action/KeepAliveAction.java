package org.laxio.protocol.action;

import org.laxio.api.protocol.ProtocolAction;

public interface KeepAliveAction extends ProtocolAction<KeepAliveAction.KeepAliveParams> {

    class KeepAliveParams {

        private final long timestamp;

        public KeepAliveParams(long timestamp) {
            this.timestamp = timestamp;
        }

        public long getTimestamp() {
            return timestamp;
        }

    }

}
