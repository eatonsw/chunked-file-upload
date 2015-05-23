package eaton.sw.upload;

public class ReceivedChunk {
    private String fileName;
    private int chunkNumber;
    private int totalChunks;
    private byte[] chunkData;
    
    public ReceivedChunk(String fileName, int chunkNumber, int totalChunks, byte[] chunkData) {
        this.fileName = fileName;
        this.chunkNumber = chunkNumber;
        this.totalChunks = totalChunks;
        this.chunkData = chunkData;
    }
    
    public String getFileName() {
        return fileName;
    }
    public int getChunkNumber() {
        return chunkNumber;
    }
    public int getTotalChunks() {
        return totalChunks;
    }
    public byte[] getChunkData() {
        return chunkData;
    }
    
    @Override
    public String toString() {
        return "Chunk " + chunkNumber + " of " + totalChunks + " for file " + fileName;
    }
}
