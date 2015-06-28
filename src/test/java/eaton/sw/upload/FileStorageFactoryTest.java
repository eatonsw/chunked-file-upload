package eaton.sw.upload;

import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class FileStorageFactoryTest extends BaseStorageFactoryTest {    
    @Rule
    public TemporaryFolder testDirectory = new TemporaryFolder();

    @Override
    protected StorageFactory createStorageFactory() {
        return new FileStorageFactory(testDirectory.getRoot().getAbsolutePath());
    }   
}
