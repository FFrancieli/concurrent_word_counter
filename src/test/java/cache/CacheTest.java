package cache;

import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

public class CacheTest {

    @Test
    public void addsEntryOnCache() throws Exception {
        Cache cache = new Cache();

        cache.cache("key", Collections.singletonList("value"));

        assertThat(cache.getCache().get("key"), hasItem("value"));
    }
}