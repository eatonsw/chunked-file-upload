package eaton.sw.upload;


public class InMemoryStorageFactory extends AbstractStorageFactory {
    @Override
    protected StorageLocation createEntityForChunk(ReceivedChunk chunk) {
        return new InMemoryStorageLocation();
    }

    @Override
    protected StorageLocation createEntityForFile(ChunkedFile file) {
        return new InMemoryStorageLocation();
    }
}
