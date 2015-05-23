package eaton.sw.upload;

public interface StorageFactory {
    StorageLocation storeChunk(ReceivedChunk chunk) throws StorageException;
    StorageLocation combineChunks(ChunkedFile file) throws StorageException;
}
