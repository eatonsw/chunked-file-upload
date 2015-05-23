package eaton.sw.upload;

import static eaton.sw.upload.ChunkedFileUploadTestFixture.CHUNK_1;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class InMemoryStorageLocationTest {
    private InMemoryStorageLocation testLocation;

    @Test
    public void testLocationCreation() throws StorageException {
        testLocation = new InMemoryStorageLocation(CHUNK_1);

        assertArrayEquals(CHUNK_1, testLocation.getData());
    }

    @Test
    public void testLocationDeletion() throws StorageException {
        testLocation = new InMemoryStorageLocation(CHUNK_1);

        assertNotNull(testLocation.getData());
        
        testLocation.delete();
        
        assertNull(testLocation.getData());
    }
    
    @Test
    public void testLocationInputStream() throws Exception {
        testLocation = new InMemoryStorageLocation(CHUNK_1);
        
        byte[] bytes = new byte[CHUNK_1.length];
        testLocation.getInputStream().read(bytes);
        
        assertArrayEquals(CHUNK_1, bytes);
    }
}
