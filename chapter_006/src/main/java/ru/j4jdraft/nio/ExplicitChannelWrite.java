package ru.j4jdraft.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Demonstrates writing to a file using NIO.
 */
public class ExplicitChannelWrite {
    public static void main(String[] args) {
        try (FileChannel channel = (FileChannel) Files.newByteChannel(Paths.get("txt/test.txt"),
                StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            final int numLetters = 26;
            ByteBuffer buffer = ByteBuffer.allocate(numLetters);
            for (int i = 0; i < numLetters; i++) {
                buffer.put((byte) ('A' + i));
            }
            buffer.rewind();
            channel.write(buffer);
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
    }
}
