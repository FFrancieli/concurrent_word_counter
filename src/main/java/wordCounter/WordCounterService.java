package wordCounter;

import cache.Cache;
import word.Word;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WordCounterService {

    private final ExecutorService executorService;
    private WordCountTask wordCountTask;

    public WordCounterService(ExecutorService executorService, WordCountTask wordCountTask) {
        this.executorService = executorService;
        this.wordCountTask = wordCountTask;
    }

    public WordCounterService(Cache cache) {
        List<List<String>> cacheContent = cache.asList();
        wordCountTask = new WordCountTask(cacheContent.get(0), cacheContent.get(1));

        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public Future<List<Word>> countWordsFrequency() {
        executorService.submit(wordCountTask);
        executorService.shutdown();
        return null;
    }
}
