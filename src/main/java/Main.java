import cache.Cache;
import fileReader.FileReaderService;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        File firstFile = new File("src/main/resources/first_file.txt");
        File seconFile = new File("src/main/resources/second_file.txt");
        List<File> files = Arrays.asList(firstFile, seconFile);

        Cache cache = new Cache();
        FileReaderService fileReaderService = new FileReaderService(files, cache);
        fileReaderService.readFile();

        System.out.println("done!");


        System.out.println(cache.getCache().size());
    }
}
