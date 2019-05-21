package org.laxio.netty.stream;

import io.netty.buffer.ByteBuf;
import org.laxio.protocol.stream.LaxioInput;
import org.laxio.protocol.stream.LaxioOutput;

import java.io.IOException;
import java.util.UUID;

public class LaxioWrappedByteBufIO implements LaxioInput, LaxioOutput {

    private final ByteBuf buf;

    public LaxioWrappedByteBufIO(ByteBuf buf) {
        this.buf = buf;
    }

    @Override
    public boolean readBoolean() throws IOException {
        return buf.readBoolean();
    }

    @Override
    public void writeBoolean(boolean out) throws IOException {
        buf.writeBoolean(out);
    }

    @Override
    public byte readByte() throws IOException {
        return buf.readByte();
    }

    @Override
    public void writeByte(byte out) throws IOException {
        buf.writeByte(out);
    }

    @Override
    public int readUnsignedByte() throws IOException {
        return buf.readUnsignedByte();
    }

    @Override
    public void writeUnsignedByte(int out) throws IOException {
        throw new UnsupportedOperationException("Currently not implemented in ByteBuf");
    }

    @Override
    public short readShort() throws IOException {
        return buf.readShort();
    }

    @Override
    public void writeShort(short out) throws IOException {
        buf.writeShort(out);
    }

    @Override
    public int readUnsignedShort() throws IOException {
        return buf.readUnsignedShort();
    }

    @Override
    public void writeUnsignedShort(int out) throws IOException {
        throw new UnsupportedOperationException("Currently not implemented in ByteBuf");
    }

    @Override
    public int readInt() throws IOException {
        return buf.readInt();
    }

    @Override
    public void writeInt(int out) throws IOException {
        buf.writeInt(out);
    }

    @Override
    public long readLong() throws IOException {
        return buf.readLong();
    }

    @Override
    public void writeLong(long out) throws IOException {
        buf.writeLong(out);
    }

    @Override
    public float readFloat() throws IOException {
        return buf.readFloat();
    }

    @Override
    public void writeFloat(float out) throws IOException {
        buf.writeFloat(out);
    }

    @Override
    public double readDouble() throws IOException {
        return buf.readDouble();
    }

    @Override
    public void writeDouble(double out) throws IOException {
        buf.writeDouble(out);
    }

    @Override
    public String readString() throws IOException {
        int length = readVarInt();
        byte[] content = readBytes(length);
        return new String(content);
    }

    @Override
    public void writeString(String out) throws IOException {
        byte[] content = out.getBytes();
        writeVarInt(content.length);
        writeBytes(content, content.length);
    }

    private long readVarFrame(int maxWidth) throws IOException {
        int numRead = 0;
        long result = 0;
        byte read;
        do {
            read = readByte();
            long value = (read & 0b01111111);
            result |= (value << (7 * numRead));

            numRead++;
            if (numRead > maxWidth)
                throw new IOException("Frame was wider than " + maxWidth + " bytes");
        } while ((read & 0b10000000) != 0);

        return result;
    }

    private void writeVar(long value) throws IOException {
        long data = value;
        do {
            byte temp = (byte) (data & 0b01111111);
            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            data >>>= 7;
            if (data != 0) {
                temp |= 0b10000000;
            }

            writeByte(temp);
        } while (data != 0);
    }

    @Override
    public int readVarInt() throws IOException {
        return (int) readVarFrame(5);
    }

    @Override
    public void writeVarInt(int out) throws IOException {
        // creates an unsigned integer, in the form of a long
        int dup = out;
        long value = 0;
        for (int i = 0; i < Integer.SIZE; i++) {
            value <<= 1;
            value |= (dup & Integer.MIN_VALUE) >>> Integer.SIZE - 1;
            dup <<= 1;
        }

        writeVar(value);
    }

    @Override
    public long readVarLong() throws IOException {
        return readVarFrame(10);
    }

    @Override
    public void writeVarLong(long out) throws IOException {
        writeVar(out);
    }

    @Override
    public UUID readUUID() throws IOException {
        return new UUID(readLong(), readLong());
    }

    @Override
    public void writeUUID(UUID out) throws IOException {
        writeLong(out.getMostSignificantBits());
        writeLong(out.getLeastSignificantBits());
    }

    @Override
    public UUID readDashedUUID() throws IOException {
        return UUID.fromString(readString());
    }

    @Override
    public void writeDashedUUID(UUID out) throws IOException {
        writeString(out.toString());
    }

    @Override
    public byte[] readBytes(int length) throws IOException {
        return buf.readBytes(new byte[length]).array();
    }

    @Override
    public void writeBytes(byte[] out, int length) throws IOException {
        buf.writeBytes(out, 0, length);
    }

}
