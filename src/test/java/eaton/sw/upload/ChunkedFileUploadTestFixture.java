package eaton.sw.upload;

public final class ChunkedFileUploadTestFixture {
    private ChunkedFileUploadTestFixture() {
    }

    public static final byte[] CHUNK_1 = { 1, 3, 5, 7, 9 };
    public static final byte[] CHUNK_2 = { 11, 13, 15, 17, 19 };
    public static final byte[] CHUNK_3 = { 21, 23, 25, 27, 29, 31, 33, 35, 37, 39 };
    
    public static final String COMPLETED_FILE_1_NAME = "TestFileOne.txt";
    public static final int COMPLETED_FILE_1_CHUNKS = 3;
    public static final byte[] COMPLETED_FILE_1_DATA = { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39 };

    public static final ReceivedChunk RECEIVED_CHUNK_1 = new ReceivedChunk(COMPLETED_FILE_1_NAME, 1, COMPLETED_FILE_1_CHUNKS, CHUNK_1);
    public static final ReceivedChunk RECEIVED_CHUNK_2 = new ReceivedChunk(COMPLETED_FILE_1_NAME, 2, COMPLETED_FILE_1_CHUNKS, CHUNK_2);
    public static final ReceivedChunk RECEIVED_CHUNK_3 = new ReceivedChunk(COMPLETED_FILE_1_NAME, 3, COMPLETED_FILE_1_CHUNKS, CHUNK_3);
}
