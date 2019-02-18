package ru.j4jdraft.nio;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Demonstrates using a mapped file to read a file.
 */
public class MappedChannelRead {
    public static void main(String[] args) {
        try (FileChannel channel = (FileChannel) Files.newByteChannel(Paths.get("txt", "test.txt"))) {
            long size = channel.size();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
            for (int i = 0; i < size; i++) {
                System.out.print((char) buffer.get());
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
    }
}
