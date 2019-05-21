package org.laxio.protocol.packet;

import org.laxio.api.protocol.Packet;
import org.laxio.protocol.stream.LaxioInput;
import org.laxio.protocol.stream.LaxioOutput;

import java.io.IOException;

public interface NetworkPacket extends Packet {

    void read(LaxioInput input) throws IOException;

    void write(LaxioOutput output) throws IOException;

}
