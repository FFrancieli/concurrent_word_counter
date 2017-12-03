import wordCounter.WordCounterExecutorService;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File firstFile = new File("src/main/resources/first_file.txt");
        File seconFile = new File("src/main/resources/second_file.txt");

        WordCounterExecutorService service = new WordCounterExecutorService(firstFile, seconFile);
        service.countWordFrequencyFromFiles();

        System.out.println("done!");
    }
}