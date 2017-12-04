package tasks;

import cache.Cache;
import fileReader.FileReader;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileReaderTask implements Runnable {

    private final File file;
    private final FileReader fileReader;
    private final Cache cache;

    public FileReaderTask(FileReader fileReader, File file, Cache cache) {
        this.file = file;
        this.fileReader = fileReader;
        this.cache = cache;
    }

    public FileReaderTask(File file) {
        this.file = file;
        this.fileReader = new FileReader();
        this.cache = new Cache();
    }

    public void run() {
        readFile().run();
    }

    private Runnable readFile() {
        return () -> {
            try {
                List<String> words = fileReader.read(file);
                cache.cache(file.getName(), words);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
