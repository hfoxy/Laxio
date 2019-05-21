package org.laxio.protocol.stream;

import java.io.IOException;
import java.util.UUID;

public interface LaxioInput {

    boolean readBoolean() throws IOException;

    byte readByte() throws IOException;

    int readUnsignedByte() throws IOException;

    short readShort() throws IOException;

    int readUnsignedShort() throws IOException;

    int readInt() throws IOException;

    long readLong() throws IOException;

    float readFloat() throws IOException;

    double readDouble() throws IOException;

    String readString() throws IOException;

    int readVarInt() throws IOException;

    long readVarLong() throws IOException;

    UUID readUUID() throws IOException;

    UUID readDashedUUID() throws IOException;

    byte[] readBytes(int length) throws IOException;

}
