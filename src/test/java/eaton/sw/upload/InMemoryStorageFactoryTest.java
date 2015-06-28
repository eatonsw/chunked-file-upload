package eaton.sw.upload;


public class InMemoryStorageFactoryTest extends BaseStorageFactoryTest {

    @Override
    protected StorageFactory createStorageFactory() {
        return new InMemoryStorageFactory();
    }
}
