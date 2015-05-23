package eaton.sw.upload;

public interface ChunkedFileCompletionListener {
    void fileCompleted(String fileName, StorageLocation completedFile);
}
