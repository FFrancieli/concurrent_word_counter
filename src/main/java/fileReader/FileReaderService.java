package fileReader;

import cache.Cache;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class FileReaderService {

    private final ExecutorService executor;
    private List<Runnable> tasks;
    private Cache cache;

    public FileReaderService(ExecutorService executor, List<File> files) {
        this.executor = executor;
        tasks = createTasks(files);
    }

    public FileReaderService(List<File> files, Cache cache) {
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        this.cache = cache;
        this.tasks = createTasks(files);
    }

    private List<Runnable> createTasks(List<File> files) {
        return files.stream().map(task -> new FileReaderTask(task, cache)).collect(Collectors.toList());
    }

    public void readFile() {
        tasks.stream().map(executor::submit).collect(Collectors.toList());

        executor.shutdown();
    }

    public List<Runnable> getTasks() {
        return tasks;
    }
}
