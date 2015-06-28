package eaton.sw.upload;

import java.util.Map;

public abstract class AbstractStorageFactory implements StorageFactory {
    @Override
    public StorageLocation storeChunk(ReceivedChunk chunk) throws StorageException {
        StorageLocation newEntity = createEntityForChunk(chunk);

        try {
            newEntity.writeBytes(chunk.getChunkData());
            return newEntity;
        } catch (Exception e) {
            throw new StorageException(e);
        }
    }

    @Override
    public StorageLocation combineChunks(ChunkedFile file) throws StorageException {
        StorageLocation newEntity = createEntityForFile(file);

        try {
            file.getChunks().entrySet().stream().sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(entry -> combine(entry.getValue(), newEntity));
            return newEntity;
        } catch (Exception e) {
            newEntity.deleteQuietly();
            throw new StorageException(e);
        } finally {
            file.getChunks().entrySet().stream().forEach(entry -> entry.getValue().deleteQuietly());
        }
    }
    
    protected void combine(StorageLocation source, StorageLocation destination) {
        try {
            destination.writeBytes(source.readAllBytes());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    protected abstract StorageLocation createEntityForChunk(ReceivedChunk chunk);

    protected abstract StorageLocation createEntityForFile(ChunkedFile file);
}
