package eaton.sw.upload;

import java.io.IOException;
import java.io.InputStream;

public interface StorageLocation {
    void writeBytes(byte[] bytes) throws IOException;
    
    InputStream inputStream() throws IOException;

    byte[] readAllBytes() throws IOException;

    void delete() throws IOException;

    default void deleteQuietly() {
        try {
            delete();
        } catch (Exception e) {
        }
    }
}
