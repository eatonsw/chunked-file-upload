package eaton.sw.upload;

import static eaton.sw.upload.ChunkedFileUploadTestFixture.CHUNK_1;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class InMemoryStorageLocationTest {
    private InMemoryStorageLocation testLocation;

    @Test
    public void testLocationCreation() throws Exception {
        testLocation = new InMemoryStorageLocation(CHUNK_1);

        assertArrayEquals(CHUNK_1, testLocation.readAllBytes());
    }

    @Test
    public void testLocationDeletion() throws Exception {
        testLocation = new InMemoryStorageLocation(CHUNK_1);

        assertNotNull(testLocation.readAllBytes());
        
        testLocation.delete();
        
        assertNotNull(testLocation.readAllBytes());
        assertEquals(0, testLocation.readAllBytes().length);
    }
}
