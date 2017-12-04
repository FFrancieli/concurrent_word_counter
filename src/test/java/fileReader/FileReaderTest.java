package fileReader;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FileReaderTest {

    FileReader fileReader;

    @Before
    public void setUp() throws Exception {
        fileReader = new FileReader();
    }

    @Test
    public void returnsArrayOfWordsOnSingleLineFile() throws Exception {
        File singleLineFile = createInMemoryTemporaryFile(Arrays.asList("one single line file content"));

        List<String> words = fileReader.read(singleLineFile);

        assertThat(words.size(), is(5));
        assertThat(words, hasItems("one", "single", "line", "file", "content"));
    }

    @Test
    public void returnsListOfWordsOnMultipleLinesFile() throws Exception {
        List<String> fileContent = Arrays.asList("first line", "second line", "third line");
        File multipleLinesFile = createInMemoryTemporaryFile(fileContent);

        List<String> words = fileReader.read(multipleLinesFile);

        assertThat(words.size(), is(6));
        assertThat(words, hasItems("first", "line", "second", "third"));
    }

    private File createInMemoryTemporaryFile(List<String> fileContent) throws IOException {
        File file = File.createTempFile("test", "txt");
        file.deleteOnExit();

        Files.write(file.toPath(), fileContent, Charset.forName("UTF-8"));

        return file;
    }
}