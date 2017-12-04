package cache;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CacheTest {

    @Test
    public void addsEntryOnCache() throws Exception {
        Cache cache = new Cache();

        cache.cache("key", Collections.singletonList("value"));

        assertThat(cache.getCache().get("key"), hasItem("value"));
    }

    @Test
    public void returnsEmptyListWhenCacheIsEmpty() throws Exception {
        Cache cache = new Cache();

        List<List<String>> cacheContent = cache.asList();

        assertThat(cacheContent.isEmpty(), is(true));
    }

    @Test
    public void returnsCacheContentAsListWhenThereIsOnlyOneEntry() throws Exception {
        Cache cache = new Cache();
        cache.cache("key", Arrays.asList("some", "word"));

        List<List<String>> cacheContent = cache.asList();

        assertThat(cacheContent.size(), is(1));

        List<String> wordList = cacheContent.get(0);
        assertThat(wordList, hasItems("some", "word"));
    }

    @Test
    public void returnsCacheContentAsListWhenThereAraMultipleEntries() throws Exception {
        Cache cache = new Cache();
        cache.cache("key", Arrays.asList("some", "word"));
        cache.cache("anotherKey", Collections.singletonList("hello"));

        List<List<String>> cacheContent = cache.asList();

        assertThat(cacheContent.size(), is(2));

        assertThat(cacheContent.get(0), hasItem("hello"));
        assertThat(cacheContent.get(1), hasItems("some", "word"));
    }
}