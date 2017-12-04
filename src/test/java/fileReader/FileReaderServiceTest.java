package fileReader;

import cache.Cache;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class FileReaderServiceTest {

    @Mock
    ExecutorService executor;

    List files;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        File firstFile = new File("some/file/path");
        File secondFile = new File("another/file/path");

        files = Arrays.asList(firstFile, secondFile);
    }

    @Test
    public void executesReadFileTask() throws Exception {
        FileReaderService wordCounterService = new FileReaderService(executor, files);
        wordCounterService.readFile();

        verify(executor, times(2)).submit(any(FileReaderTask.class));
    }

    @Test
    public void shutsDownTaskExecutor() throws Exception {
        FileReaderService wordCounterService = new FileReaderService(executor, files);
        wordCounterService.readFile();

        verify(executor).shutdown();
    }

    @Test
    public void createsListOfFileReaderTasksOnInitialize() throws Exception {
        FileReaderService fileReaderService = new FileReaderService(files, mock(Cache.class));

        List<Runnable> tasks = fileReaderService.getTasks();

        assertThat(tasks.size(), is(2));
    }
}