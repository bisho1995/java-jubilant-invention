package googleguava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class CacheLoaderPoc1Test {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void whenCacheMiss_thenValueIsComputed() throws ExecutionException {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return key.toUpperCase();
            }
        };

        LoadingCache<String, String> cache;
        cache= CacheBuilder.newBuilder().build(loader);

        assertEquals(0, cache.size());
        assertEquals("HELLO", cache.getUnchecked("hello"));
        assertEquals("HELLO1", cache.get("hello1"));
        assertEquals(2, cache.size());
    }

    @Test
    public void whenCacheReachMaxSize_thenEviction() {
        CacheLoader<String, String> loader = new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                return s.toUpperCase();
            }
        };

        LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumSize(3).build(loader);
        cache.getUnchecked("first");
        cache.getUnchecked("second");
        cache.getUnchecked("third");
        cache.getUnchecked("forth");

        assertEquals(3, cache.size());
        assertNull(cache.getIfPresent("first"));
        assertEquals("FORTH", cache.getIfPresent("forth"));
    }

    @Test
    public void whenCacheReachMaximumWeight_thenEviction(){
        CacheLoader<String, String> loader = new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                return s.toUpperCase();
            }
        };

        Weigher<String, String> weighByLength=new Weigher<String, String>() {
            @Override
            public int weigh(String key, String value) {
                return value.length();
            }
        };

        LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumWeight(16).weigher(weighByLength).build(loader);
        cache.getUnchecked("first");
        cache.getUnchecked("second");
        cache.getUnchecked("third");
        cache.getUnchecked("last");
        assertEquals(3, cache.size());
        assertNull(cache.getIfPresent("first"));
        assertEquals("LAST", cache.getIfPresent("last"));
    }

}