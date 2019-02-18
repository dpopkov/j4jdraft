package ru.j4jdraft.io.zip;

import ru.j4jdraft.io.FileExtensionFilter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Contains methods for compressing folders.
 */
public class ZipFolder {
    /**
     * Compresses the specified directory.
     * @param directory path to directory
     * @param output path to output file
     * @param excludeExt list of extensions to exclude from compressing
     *                   or null to compress all files
     * @return path to output file
     * @throws IOException if an I/O error occurs
     */
    public Path compress(String directory, String output, List<String> excludeExt) throws IOException {
        return compress(Paths.get(directory), Paths.get(output), excludeExt);
    }

    /**
     * Compresses the specified directory.
     * @param directory path to directory
     * @param output path to output file
     * @param excludeExt list of extensions to exclude from compressing
     *                   or null to compress all files
     * @return path to output file
     * @throws IOException if an I/O error occurs
     */
    public Path compress(Path directory, Path output, List<String> excludeExt) throws IOException {
        FileExtensionFilter exclude = new FileExtensionFilter(excludeExt);
        try (ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(output))) {
            Files.walk(directory).forEach(entry -> {
                if (Files.isRegularFile(entry) && (excludeExt == null || exclude.reject(entry))) {
                    Path sourcePath = directory.relativize(entry);
                    try {
                        out.putNextEntry(new ZipEntry(sourcePath.toString()));
                        byte[] bytes = Files.readAllBytes(entry);
                        out.write(bytes, 0, bytes.length);
                        out.closeEntry();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        return output;
    }
}
