package fileReader;

import org.junit.Test;

import java.io.File;
import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.*;

public class FileReaderServiceTest {

    @Test
    public void executesReadFileTask() throws Exception {
        ExecutorService executor = mock(ExecutorService.class);
        File firstFile = new File("some/file/path");
        File secondFile = new File("another/file/path");

        FileReaderService wordCounterservice = new FileReaderService(executor, firstFile, secondFile);
        wordCounterservice.countWordFrequencyFromFiles();

        verify(executor, times(2)).submit(any(FileReaderTask.class));
    }

    @Test
    public void shutsDownTaskExecutor() throws Exception {
        ExecutorService executor = mock(ExecutorService.class);
        File firstFile = new File("some/file/path");
        File secondFile = new File("another/file/path");

        FileReaderService wordCounterservice = new FileReaderService(executor, firstFile, secondFile);
        wordCounterservice.countWordFrequencyFromFiles();

        verify(executor).shutdown();
    }
}