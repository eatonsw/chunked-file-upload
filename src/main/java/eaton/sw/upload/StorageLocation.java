package eaton.sw.upload;

import java.io.InputStream;

public interface StorageLocation {
    byte[] getData() throws StorageException;
    InputStream getInputStream() throws StorageException;
    void delete() throws StorageException;
}
