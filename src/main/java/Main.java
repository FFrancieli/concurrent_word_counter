import cache.Cache;
import fileReader.FileReaderServiceExecutor;
import word.Word;
import wordCounter.WordCounterServiceExecutor;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
        public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        File firstFile = new File("src/main/resources/first_file.txt");
        File secondFile = new File("src/main/resources/second_file.txt");
        List<File> files = Arrays.asList(firstFile, secondFile);

        Cache cache = new Cache();
        FileReaderServiceExecutor fileReaderServiceExecutor = new FileReaderServiceExecutor(files, cache);
        fileReaderServiceExecutor.readFile();

        WordCounterServiceExecutor wordCounterServiceExecutor = new WordCounterServiceExecutor(cache);
        List<Word> wordFrequency = wordCounterServiceExecutor.countWordsFrequency();

        wordFrequency.forEach(System.out::println);
    }
}
