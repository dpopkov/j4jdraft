package ru.j4jdraft.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Demonstrates reading a file via a channel.
 */
public class ExplicitChannelRead {
    public static void main(String[] args) {
        Path filepath;
        try {
            filepath = Paths.get("txt/test.txt");
        } catch (InvalidPathException e) {
            System.out.println("Path error: " + e);
            return;
        }
        try (SeekableByteChannel channel = Files.newByteChannel(filepath)) {
            ByteBuffer buffer = ByteBuffer.allocate(128);
            int count;
            do {
                count = channel.read(buffer);
                if (count != -1) {
                    buffer.rewind();
                    for (int i = 0; i < count; i++) {
                        System.out.print((char) buffer.get());
                    }
                }
            } while (count != -1);
            System.out.println();
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
    }
}
