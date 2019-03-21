import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheWriteSynchronizationMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;

public class PositionStore {

    private static final String STORAGE_CACHE = PositionStore.class.getSimpleName() + "Cache";
    private Ignite positionStoreIgnite;
    private IgniteCache<String, Position> positioncache;

    public void init() {

        Ignite ignite = Ignition.start(getIgniteConfig());
        CacheConfiguration<String, Position> positioncacheConfig = new CacheConfiguration<String, Position>(STORAGE_CACHE);
        positioncacheConfig.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
        positioncacheConfig.setBackups(1);
        positioncacheConfig.setWriteSynchronizationMode(CacheWriteSynchronizationMode.FULL_SYNC);
        positioncacheConfig.setIndexedTypes(String.class, Position.class);

        positioncache = ignite.getOrCreateCache(positioncacheConfig);

    }

    public IgniteConfiguration getIgniteConfig() {
        IgniteConfiguration cfg = new IgniteConfiguration();
        DataStorageConfiguration storageCfg = new DataStorageConfiguration();
        storageCfg.getDefaultDataRegionConfiguration().setPersistenceEnabled(true);
        cfg.setDataStorageConfiguration(storageCfg);

        return cfg;
    }


}
