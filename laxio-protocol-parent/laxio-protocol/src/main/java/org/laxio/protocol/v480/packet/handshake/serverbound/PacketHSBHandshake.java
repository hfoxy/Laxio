package org.laxio.protocol.v480.packet.handshake.serverbound;

import org.laxio.protocol.packet.NetworkPacket;
import org.laxio.protocol.stream.LaxioInput;
import org.laxio.protocol.stream.LaxioOutput;

import java.io.IOException;

public class PacketHSBHandshake implements NetworkPacket {

    private int protocolVersion;
    private String serverAddress;
    private int port;
    private int nextState;

    public PacketHSBHandshake() {
        // required by Laxio
    }

    public PacketHSBHandshake(int protocolVersion, String serverAddress, int port, int nextState) {
        this.protocolVersion = protocolVersion;
        this.serverAddress = serverAddress;
        this.port = port;
        this.nextState = nextState;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public int getPort() {
        return port;
    }

    public int getNextState() {
        return nextState;
    }

    @Override
    public void read(LaxioInput input) throws IOException {
        protocolVersion = input.readVarInt();
        serverAddress = input.readString();
        port = input.readUnsignedShort();
        nextState = input.readVarInt();
    }

    @Override
    public void write(LaxioOutput output) throws IOException {
        output.writeVarInt(protocolVersion);
        output.writeString(serverAddress);
        output.writeUnsignedShort(port);
        output.writeVarInt(nextState);
    }

}
