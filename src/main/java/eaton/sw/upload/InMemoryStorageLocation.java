package eaton.sw.upload;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class InMemoryStorageLocation implements StorageLocation {
    byte[] data;
    
    public InMemoryStorageLocation() {
        this.data = new byte[0];
    }
    
    public InMemoryStorageLocation(byte[] data) {
        checkNotNull(data);
        this.data = data;
    }
    
    @Override
    public void writeBytes(byte[] bytes) throws IOException {
        byte[] newData = Arrays.copyOf(data, data.length + bytes.length);
        System.arraycopy(bytes, 0, newData, data.length, bytes.length);
        data = newData;
    }
    
    @Override
    public InputStream inputStream() throws IOException {
        return new ByteArrayInputStream(data);
    }

    @Override
    public byte[] readAllBytes() throws IOException {
        return data;
    }

    @Override
    public void delete() throws IOException {
        data = new byte[0];
    }
}
