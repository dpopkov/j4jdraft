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
            try {
                Files.walk(directory)
                        .filter(entry -> Files.isRegularFile(entry) && (excludeExt == null || exclude.reject(entry)))
                        .forEach(entry -> writeEntryToZip(out, directory, entry));
            } catch (WrappedIOException e) {
                if (e.getCause() instanceof IOException) {
                    throw (IOException) e.getCause();
                }
                throw e;
            }
        }
        return output;
    }

    /**
     * Writes the specified entry to zip output stream.
     * @param out output to zip
     * @param baseDir base directory, all files of which are archived
     * @param entry path to every file that should be zipped
     * @throws WrappedIOException if an I/O error has occurred this exception saves the cause
     */
    private void writeEntryToZip(ZipOutputStream out, Path baseDir, Path entry) {
        try {
            out.putNextEntry(new ZipEntry(baseDir.relativize(entry).toString()));
            out.write(Files.readAllBytes(entry));
            out.closeEntry();
        } catch (IOException e) {
            throw new WrappedIOException(e);
        }
    }

    /**
     * Runtime exception wrapper for {@code IOException}.
     */
    public static class WrappedIOException extends RuntimeException {
        public WrappedIOException(IOException cause) {
            super(cause);
        }
    }
}
