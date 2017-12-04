package wordCounter;

import word.Word;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class WordCounterService {

    private final ExecutorService executorService;

    public WordCounterService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Future<List<Word>> countWordsFrequency() {
        executorService.submit(new WordCountTask());
        executorService.shutdown();
        return null;
    }
}
