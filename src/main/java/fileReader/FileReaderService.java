package fileReader;

import tasks.FileReaderTask;
import word.Words.Word;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class FileReaderService {

    private final ExecutorService executor;
    private List<Runnable> tasks;

    public FileReaderService(ExecutorService executor, File firstFile, File secondFile) {
        this.executor = executor;
        tasks = Arrays.asList(new FileReaderTask(firstFile), new FileReaderTask(secondFile));
    }

    public FileReaderService(File firstFile, File secondFile) {
        executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        tasks = Arrays.asList(new FileReaderTask(firstFile), new FileReaderTask(secondFile));
    }

    public List<Word> countWordFrequencyFromFiles() {
        tasks.stream().map(executor::submit).collect(Collectors.toList());

        executor.shutdown();
        return null;
    }
}
