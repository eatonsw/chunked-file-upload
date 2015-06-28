package eaton.sw.upload;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ChunkedFileUpload {
    private static final Logger LOG = LoggerFactory.getLogger(ChunkedFileUpload.class);

    private StorageFactory storageFactory;
    private Map<String, ChunkedFile> files;
    private List<ChunkedFileCompletionListener> listeners;

    public ChunkedFileUpload(StorageFactory storageFactory) {
        this.storageFactory = storageFactory;

        files = Maps.newConcurrentMap();
        listeners = Lists.newArrayList();
    }
    
    public void addListener(ChunkedFileCompletionListener listener) {
        listeners.add(listener);
    }
    
    private void notifyListeners(String fileName, StorageLocation location) {
        listeners.stream().forEach(listener -> listener.fileCompleted(fileName, location));
    }

    public boolean hasChunk(Chunk chunk) {
        if (files.containsKey(chunk.getFileName())) {
            return files.get(chunk.getFileName()).hasChunk(chunk.getChunkNumber());
        }

        return false;
    }

    public void receivedChunk(ReceivedChunk chunk) throws StorageException {
        if (!files.containsKey(chunk.getFileName())) {
            files.put(chunk.getFileName(), new ChunkedFile(chunk.getFileName(), chunk.getTotalChunks()));
        }

        ChunkedFile file = files.get(chunk.getFileName());

        if (file.hasChunk(chunk.getChunkNumber())) {
            LOG.warn("Already received {}", chunk);
            return;
        }

        file.addChunk(chunk.getChunkNumber(), storageFactory.storeChunk(chunk));
        
        if (file.isComplete()) {
            LOG.info("Received all chunks for file {}", file.getFileName());
            
            StorageLocation completedFile = storageFactory.combineChunks(file);
            notifyListeners(file.getFileName(), completedFile);
            files.remove(file.getFileName());
            completedFile.deleteQuietly();
        }
    }
}
