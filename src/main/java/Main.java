import cache.Cache;
import fileReader.FileReaderService;
import word.Word;
import wordCounter.WordCounterService;

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
        FileReaderService fileReaderService = new FileReaderService(files, cache);
        fileReaderService.readFile();

        WordCounterService wordCounterService = new WordCounterService(cache);
        List<Word> wordFrequency = wordCounterService.countWordsFrequency();

        wordFrequency.forEach(System.out::println);
    }
}
