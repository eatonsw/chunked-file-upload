package eaton.sw.upload;

import static eaton.sw.upload.ChunkedFileUploadTestFixture.COMPLETED_FILE_1_NAME;
import static eaton.sw.upload.ChunkedFileUploadTestFixture.RECEIVED_CHUNK_1;
import static eaton.sw.upload.ChunkedFileUploadTestFixture.RECEIVED_CHUNK_2;
import static eaton.sw.upload.ChunkedFileUploadTestFixture.RECEIVED_CHUNK_3;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ChunkedFileUploadTest {
    private ChunkedFileUpload upload;
    private TestChunkedFileCompletionListener listener;
    
    @Before
    public void setUp() {
        listener = new TestChunkedFileCompletionListener();
        upload = new ChunkedFileUpload(new InMemoryStorageFactory());
        upload.addListener(listener);
    }
    
    @Test
    public void testHasChunk() throws Exception {
        upload.receivedChunk(RECEIVED_CHUNK_1);
        
        assertTrue(upload.hasChunk(new Chunk(COMPLETED_FILE_1_NAME, 1)));
    }
    
    @Test
    public void testChunkedUpload() throws Exception {
        upload.receivedChunk(RECEIVED_CHUNK_1);
        upload.receivedChunk(RECEIVED_CHUNK_2);
        
        assertTrue(upload.hasChunk(new Chunk(COMPLETED_FILE_1_NAME, 1)));
        assertTrue(upload.hasChunk(new Chunk(COMPLETED_FILE_1_NAME, 2)));
        assertFalse(upload.hasChunk(new Chunk(COMPLETED_FILE_1_NAME, 3)));
        
        assertFalse(listener.isComplete());
        
        upload.receivedChunk(RECEIVED_CHUNK_3);
        
        assertTrue(listener.isComplete());
        assertFalse(upload.hasChunk(new Chunk(COMPLETED_FILE_1_NAME, 1)));
        assertFalse(upload.hasChunk(new Chunk(COMPLETED_FILE_1_NAME, 2)));
        assertFalse(upload.hasChunk(new Chunk(COMPLETED_FILE_1_NAME, 3)));
    }
    
    
    
    public static class TestChunkedFileCompletionListener implements ChunkedFileCompletionListener {
        private boolean complete = false;
        
        public boolean isComplete() {
            return complete;
        }
        
        @Override
        public void fileCompleted(String fileName, StorageLocation completedFile) {
            complete = true;
        }
    }
}
