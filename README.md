# Chunked-File-Upload
Java library for receiving chunked file uploads.

I had a need for a Java server side implementation to receive chunk filed uploads from FlowJS - and was shocked when I couldn't find 
a simple library that allowed me to pass in chunks and be notified of completion.  So when I put it together myself I thought I should 
put it up on Github.

It's still a work in progress.  Everything can change.  Currently looking at multiple ways to improve the Storage API portion.
  
All suggestions are welcome.

## Using the API
To use it - you'll need 3 things, a means to store the files, a listener for when files have completed, and the main service object to handle received chunks.

The listener:

```Java
public class MyListener implements ChunkedFileCompletionListener {
    public void fileCompleted(String fileName, StorageLocation completedFile) {
        System.out.println("File " + fileName + " is done");
        persistTheFileIfNeeded(completedFile.inputStream());
        // Or
        persistTheFileIfNeeded(completedFile.readAllBytes());
        
        // No need to delete the file or chunks, that happens automatically.
    }
}
```

Initializing the service:

```Java
StorageFactory storage = new FileStorageFactory("/tmp/myTempDir/");
ChunkedFileUpload upload = new ChunkedFileUpload(storage);
upload.addListener(new MyListener());
```

In use (perhaps in a Spring controller somewhere):

```Java
public boolean someoneCheckingIfTheChunkExists(String fileName, int chunkNumber) {
    return upload.hasChunk(new Chunk(fileName, chunkNumber));
}

public void someoneUploadedAChunk(String fileName, int chunkNumber, int totalChunks, byte[] data) {
    try {
        upload.receivedChunk(new ReceivedChunk(fileName, chunkedNumber, totalChunks, data));
    } catch (StorageException e) {
        System.out.println("Upload failed... you should probably tell someone");
    }
}
```