package eaton.sw.upload;

import java.nio.file.Paths;

public class FileStorageFactory extends AbstractStorageFactory {
    private String storageDirectory;
    
    public FileStorageFactory(String storageDirectory) {
        this.storageDirectory = storageDirectory;
    }

    private String generateFileName(ReceivedChunk chunk) {
        return chunk.getFileName() + "@" + chunk.getChunkNumber() + "@" + chunk.getTotalChunks();
    }

    @Override
    protected StorageLocation createEntityForChunk(ReceivedChunk chunk) {
        return new FileStorageLocation(Paths.get(storageDirectory, generateFileName(chunk)));
    }

    @Override
    protected StorageLocation createEntityForFile(ChunkedFile file) {
        return new FileStorageLocation(Paths.get(storageDirectory, file.getFileName()));
    }
}
