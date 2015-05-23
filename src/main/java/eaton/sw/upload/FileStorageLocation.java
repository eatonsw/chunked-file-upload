package eaton.sw.upload;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileStorageLocation implements StorageLocation {
    private Path file;
    
    public FileStorageLocation(Path file) {
        this.file = file;
    }
    
    @Override
    public byte[] getData() throws StorageException {
        try {
            return Files.readAllBytes(file);
        } catch (Exception e) {
            throw new StorageException("Failed to read file " + file, e);
        }
    }

    @Override
    public InputStream getInputStream() throws StorageException {
        try {
            return Files.newInputStream(file);
        } catch (Exception e) {
            throw new StorageException("Failed to open file " + file, e);
        }
    }

    @Override
    public void delete() throws StorageException {
        try {
            Files.deleteIfExists(file);
        } catch (Exception e) {
            throw new StorageException("Failed to delete the file " + file, e);
        }
    }

}
