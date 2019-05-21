package org.laxio.protocol.stream;

import java.io.IOException;
import java.util.UUID;

public class LaxioWrappedInput implements LaxioInput {

    private final LaxioInput in;

    public LaxioWrappedInput(LaxioInput in) {
        this.in = in;
    }

    @Override
    public boolean readBoolean() throws IOException {
        return in.readBoolean();
    }

    @Override
    public byte readByte() throws IOException {
        return in.readByte();
    }

    @Override
    public int readUnsignedByte() throws IOException {
        return in.readUnsignedByte();
    }

    @Override
    public short readShort() throws IOException {
        return in.readShort();
    }

    @Override
    public int readUnsignedShort() throws IOException {
        return in.readUnsignedShort();
    }

    @Override
    public int readInt() throws IOException {
        return in.readInt();
    }

    @Override
    public long readLong() throws IOException {
        return in.readLong();
    }

    @Override
    public float readFloat() throws IOException {
        return in.readFloat();
    }

    @Override
    public double readDouble() throws IOException {
        return in.readDouble();
    }

    @Override
    public String readString() throws IOException {
        return in.readString();
    }

    @Override
    public int readVarInt() throws IOException {
        return in.readVarInt();
    }

    @Override
    public long readVarLong() throws IOException {
        return in.readVarLong();
    }

    @Override
    public UUID readUUID() throws IOException {
        return in.readUUID();
    }

    @Override
    public UUID readDashedUUID() throws IOException {
        return in.readDashedUUID();
    }

    @Override
    public byte[] readBytes(int length) throws IOException {
        return in.readBytes(length);
    }

}
