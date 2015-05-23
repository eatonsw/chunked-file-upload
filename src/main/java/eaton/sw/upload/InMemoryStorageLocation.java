package eaton.sw.upload;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;

public class InMemoryStorageLocation implements StorageLocation {
    private byte[] data;
    
    public InMemoryStorageLocation(byte[] data) {
        this.data = Arrays.copyOf(data, data.length);
    }

    @Override
    public byte[] getData() throws StorageException {
        return data;
    }

    @Override
    public InputStream getInputStream() throws StorageException {
        return new ByteArrayInputStream(data);
    }

    @Override
    public void delete() throws StorageException {
        data = null;
    }
}
