package input;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

    public  List<String> read(File file) throws IOException {
        Path path = Paths.get(file.getAbsolutePath());

        List<String> lines = Files.readAllLines(path);

        return lines.stream()
                .map(line -> line.split("\\s+"))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }
}
