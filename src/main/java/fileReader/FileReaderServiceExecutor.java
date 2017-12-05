package fileReader;

import cache.Cache;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FileReaderServiceExecutor {

    private final ExecutorService executor;
    private List<Runnable> tasks;
    private Cache cache;

    public FileReaderServiceExecutor(ExecutorService executor, List<File> files) {
        this.executor = executor;
        tasks = createTasks(files);
    }

    public FileReaderServiceExecutor(List<File> files, Cache cache) {
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        this.cache = cache;
        this.tasks = createTasks(files);
    }

    private List<Runnable> createTasks(List<File> files) {
        return files.stream().map(task -> new FileReaderTask(task, cache)).collect(Collectors.toList());
    }

    public void readFile() throws InterruptedException {
        tasks.forEach(executor::submit);

        executor.shutdown();

        executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
    }

    public List<Runnable> getTasks() {
        return tasks;
    }
}
