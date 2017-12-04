package wordCounter;

import tasks.FileReaderTask;
import tasks.WordCountTask;
import word.Words.Word;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class WordCounterExecutorService {

    private final ExecutorService executor;
    private List<Runnable> tasks;

    public WordCounterExecutorService(ExecutorService executor, File firstFile, File secondFile) {
        this.executor = executor;
        tasks = Arrays.asList(new FileReaderTask(firstFile), new FileReaderTask(secondFile));
    }

    public WordCounterExecutorService(File firstFile, File secondFile) {
        executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        tasks = Arrays.asList(new FileReaderTask(firstFile), new FileReaderTask(secondFile));
    }

    public List<Word> countWordFrequencyFromFiles() {
        tasks.stream().map(executor::submit).collect(Collectors.toList());

        executor.shutdown();
        return null;
    }
}
