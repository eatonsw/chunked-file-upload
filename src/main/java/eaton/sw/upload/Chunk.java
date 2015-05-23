package eaton.sw.upload;

public class Chunk {
    private String fileName;
    private int chunkNumber;

    public Chunk(String fileName, int chunkNumber) {
        this.fileName = fileName;
        this.chunkNumber = chunkNumber;
    }
    
    public String getFileName() {
        return fileName;
    }

    public int getChunkNumber() {
        return chunkNumber;
    }
}
