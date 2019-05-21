package org.laxio.protocol.stream;

import java.io.IOException;
import java.util.UUID;

public interface LaxioOutput {

    void writeBoolean(boolean out) throws IOException;

    void writeByte(byte out) throws IOException;

    void writeUnsignedByte(int out) throws IOException;

    void writeShort(short out) throws IOException;

    void writeUnsignedShort(int out) throws IOException;

    void writeInt(int out) throws IOException;

    void writeLong(long out) throws IOException;

    void writeFloat(float out) throws IOException;

    void writeDouble(double out) throws IOException;

    void writeString(String out) throws IOException;

    void writeVarInt(int out) throws IOException;

    void writeVarLong(long out) throws IOException;

    void writeUUID(UUID out) throws IOException;

    void writeDashedUUID(UUID out) throws IOException;

    void writeBytes(byte[] out, int length) throws IOException;

}
