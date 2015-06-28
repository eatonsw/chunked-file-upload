package eaton.sw.upload;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileStorageLocation implements StorageLocation {
    private Path file;

    public FileStorageLocation(Path file) {
        this.file = file;
    }

    @Override
    public void writeBytes(byte[] bytes) throws IOException {
        Files.write(file, bytes, CREATE, WRITE, APPEND);
    }
    
    @Override
    public InputStream inputStream() throws IOException {
        return Files.newInputStream(file);
    }

    @Override
    public byte[] readAllBytes() throws IOException {
        return Files.readAllBytes(file);
    }

    @Override
    public void delete() throws IOException {
        Files.deleteIfExists(file);
    }
}
