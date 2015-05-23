package eaton.sw.upload;

import java.util.Map;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class InMemoryStorageFactory implements StorageFactory {
    @Override
    public StorageLocation storeChunk(ReceivedChunk chunk) throws StorageException {
        return new InMemoryStorageLocation(chunk.getChunkData());
    }

    @Override
    public StorageLocation combineChunks(ChunkedFile file) throws StorageException {
        ByteArrayDataOutput array = ByteStreams.newDataOutput();
        
        file.getChunks().entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEachOrdered(entry -> addDataToByteArray(entry.getValue(), array));
        
        return new InMemoryStorageLocation(array.toByteArray());
    }
    
    private void addDataToByteArray(StorageLocation location, ByteArrayDataOutput array) {
        try {
            array.write(location.getData());
            location.delete();
        } catch (StorageException e) {
            // This should never happen...
            throw new IllegalArgumentException("Failed to add data to the byte buffer!", e);
        }
    }
}
