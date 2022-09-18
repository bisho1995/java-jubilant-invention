package googleguava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ExecutionException;

public class CacheLoaderPoc2 {
    public static void main(String[] args) throws ExecutionException {
        Driver driver = new Driver();
        driver.setup();
        String res = driver.getCache().get("SomeKey");
        System.out.println("res = "+res);

        driver.setM3("some m3 value");
        System.out.println("m3 value after setting: " + driver.getM3());
        String res2 = driver.getCache().get("SomeKey1");
        System.out.println("res = "+res2);
    }
    @Data
    @Builder
    public static final class Client {
        String m3;
    }
    private static final class Driver {
        @Getter
        @Setter
        String m3;
        @Getter
        Client client;
        @Getter
        private LoadingCache<String, String> cache;

        void setup() {
            client = Client.builder().build();
            cache = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    client.setM3(m3);
                    return client.getM3() == null ? "null": client.getM3();
                }
            });
        }
    }
}
