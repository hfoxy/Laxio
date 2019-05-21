package org.laxio.protocol.stream;

import java.io.IOException;
import java.util.UUID;

public class LaxioWrappedOutput implements LaxioOutput {

    private final LaxioOutput out;

    public LaxioWrappedOutput(LaxioOutput out) {
        this.out = out;
    }

    @Override
    public void writeBoolean(boolean out) throws IOException {
        this.out.writeBoolean(out);
    }

    @Override
    public void writeByte(byte out) throws IOException {
        this.out.writeByte(out);
    }

    @Override
    public void writeUnsignedByte(int out) throws IOException {
        this.out.writeUnsignedByte(out);
    }

    @Override
    public void writeShort(short out) throws IOException {
        this.out.writeShort(out);
    }

    @Override
    public void writeUnsignedShort(int out) throws IOException {
        this.out.writeUnsignedShort(out);
    }

    @Override
    public void writeInt(int out) throws IOException {
        this.out.writeInt(out);
    }

    @Override
    public void writeLong(long out) throws IOException {
        this.out.writeLong(out);
    }

    @Override
    public void writeFloat(float out) throws IOException {
        this.out.writeFloat(out);
    }

    @Override
    public void writeDouble(double out) throws IOException {
        this.out.writeDouble(out);
    }

    @Override
    public void writeString(String out) throws IOException {
        this.out.writeString(out);
    }

    @Override
    public void writeVarInt(int out) throws IOException {
        this.out.writeVarInt(out);
    }

    @Override
    public void writeVarLong(long out) throws IOException {
        this.out.writeVarLong(out);
    }

    @Override
    public void writeUUID(UUID out) throws IOException {
        this.out.writeUUID(out);
    }

    @Override
    public void writeDashedUUID(UUID out) throws IOException {
        this.out.writeDashedUUID(out);
    }

    @Override
    public void writeBytes(byte[] out, int length) throws IOException {
        this.out.writeBytes(out, length);
    }

}
