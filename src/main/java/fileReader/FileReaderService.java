package fileReader;

import word.Word;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class FileReaderService {

    private final ExecutorService executor;
    private List<Runnable> tasks;

    public FileReaderService(ExecutorService executor, List<File> files) {
        this.executor = executor;
        tasks = files.stream().map(FileReaderTask::new).collect(Collectors.toList());
    }

    public FileReaderService(List<File> files) {
        executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        tasks = files.stream().map(FileReaderTask::new).collect(Collectors.toList());
    }

    public List<Word> countWordFrequencyFromFiles() {
        tasks.stream().map(executor::submit).collect(Collectors.toList());

        executor.shutdown();
        return null;
    }
}
