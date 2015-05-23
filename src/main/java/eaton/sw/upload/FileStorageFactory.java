package eaton.sw.upload;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class FileStorageFactory implements StorageFactory {
    private String storageDirectory;

    public FileStorageFactory(String storageDirectory) {
        this.storageDirectory = storageDirectory;
    }

    @Override
    public StorageLocation storeChunk(ReceivedChunk chunk) throws StorageException {
        Path location = Paths.get(storageDirectory, generateFileName(chunk));

        try {
            Files.write(location, chunk.getChunkData());
            return new FileStorageLocation(location);
        } catch (IOException e) {
            throw new StorageException("Failed to write data to file " + location, e);
        }
    }

    private String generateFileName(ReceivedChunk chunk) {
        return chunk.getFileName() + "@" + chunk.getChunkNumber() + "@" + chunk.getTotalChunks();
    }

    @Override
    public StorageLocation combineChunks(ChunkedFile file) throws StorageException {
        Path location = Paths.get(storageDirectory, file.getFileName());
        
        try {
            file.getChunks().entrySet().stream().sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(entry -> addDataToFile(entry.getValue(), location));
            
            return new FileStorageLocation(location);
        } catch (Exception e) {
            throw new StorageException("Failed to combine chunks for file " + file.getFileName(), e);
        }
    }

    private void addDataToFile(StorageLocation data, Path file) {
        try {
            Files.write(file, data.getData(), CREATE, WRITE, APPEND);
            data.delete();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to add data to file " + file, e);
        }
    }
}
