package wordCounter;

import cache.Cache;
import word.Word;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class WordCounterService {

    private final ExecutorService executorService;
    private final Cache cache;

    public WordCounterService(ExecutorService executorService, Cache cache) {
        this.executorService = executorService;
        this.cache = cache;
    }

    public Future<List<Word>> countWordsFrequency() {
        List<List<String>> cacheContent = cache.asList();

        executorService.submit(new WordCountTask());
        executorService.shutdown();
        return null;
    }
}
