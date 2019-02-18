package ru.j4jdraft.nio;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MappedChannelWrite {
    public static void main(String[] args) {
        try (FileChannel channel = (FileChannel) Files.newByteChannel(Paths.get("txt/test2.txt"),
                StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE)) {
            final int numLetters = 26;
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, numLetters);
            for (int i = 0; i < numLetters; i++) {
                buffer.put((byte) ('A' + i));
            }
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
    }
}
