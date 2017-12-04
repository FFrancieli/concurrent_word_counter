package wordCounter;

import cache.Cache;
import word.Word;

import java.util.List;
import java.util.concurrent.ExecutionException;
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
        wordCountTask = cacheContent.size() < 2 ? new WordCountTask() : new WordCountTask(cacheContent.get(0), cacheContent.get(1));

        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public List<Word> countWordsFrequency() throws ExecutionException, InterruptedException {
        Future<List<Word>> wordFrequency = executorService.submit(wordCountTask);
        executorService.shutdown();

        return wordFrequency.get();
    }

    protected void setWordCountTask(WordCountTask wordCountTask) {
        this.wordCountTask = wordCountTask;
    }
}
