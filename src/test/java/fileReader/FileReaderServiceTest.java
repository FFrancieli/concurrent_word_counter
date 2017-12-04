package fileReader;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.File;
import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class FileReaderServiceTest {

    @Mock
    ExecutorService executor;

    File firstFile;
    File secondFile;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        firstFile = new File("some/file/path");
        secondFile = new File("another/file/path");
    }

    @Test
    public void executesReadFileTask() throws Exception {
        FileReaderService wordCounterService = new FileReaderService(executor, firstFile, secondFile);
        wordCounterService.countWordFrequencyFromFiles();

        verify(executor, times(2)).submit(any(FileReaderTask.class));
    }

    @Test
    public void shutsDownTaskExecutor() throws Exception {
        FileReaderService wordCounterService = new FileReaderService(executor, firstFile, secondFile);
        wordCounterService.countWordFrequencyFromFiles();

        verify(executor).shutdown();
    }
}