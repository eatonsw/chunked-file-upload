package eaton.sw.upload;

@SuppressWarnings("serial")
public class StorageException extends Exception {
    public StorageException() {
        super();
    }
    
    public StorageException(String message) {
        super(message);
    }
    
    public StorageException(Throwable cause) {
        super(cause);
    }
    
    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
