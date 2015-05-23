package eaton.sw.upload;

import java.util.Map;

import com.google.common.collect.Maps;

public class ChunkedFile {
    private String fileName;
    private int totalChunks;
    private Map<Integer, StorageLocation> chunks;
    
    public ChunkedFile(String fileName, int totalChunks) {
        this.fileName = fileName;
        this.totalChunks = totalChunks;
        
        chunks = Maps.newConcurrentMap();
    }

    public String getFileName() {
        return fileName;
    }

    public int getTotalChunks() {
        return totalChunks;
    }

    public Map<Integer, StorageLocation> getChunks() {
        return chunks;
    }
    
    public boolean hasChunk(int chunkNumber) {
        return chunks.containsKey(chunkNumber);
    }
    
    public void addChunk(int chunkNumber, StorageLocation chunk) {
        chunks.put(chunkNumber, chunk);
    }
    
    public boolean isComplete() {
        return totalChunks == chunks.size();
    }
}
