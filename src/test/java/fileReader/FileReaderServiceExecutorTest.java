package fileReader;

import cache.Cache;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class FileReaderServiceExecutorTest {

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
        FileReaderServiceExecutor wordCounterService = new FileReaderServiceExecutor(executor, files);
        wordCounterService.readFile();

        verify(executor, times(2)).submit(any(FileReaderTask.class));
    }

    @Test
    public void shutsDownTaskExecutor() throws Exception {
        FileReaderServiceExecutor wordCounterService = new FileReaderServiceExecutor(executor, files);
        wordCounterService.readFile();

        verify(executor).shutdown();
    }

    @Test
    public void createsListOfFileReaderTasksOnInitialize() throws Exception {
        FileReaderServiceExecutor fileReaderServiceExecutor = new FileReaderServiceExecutor(files, mock(Cache.class));

        List<Runnable> tasks = fileReaderServiceExecutor.getTasks();

        assertThat(tasks.size(), is(2));
    }

    @Test
    public void waitsForThreadsExecutionToFinish() throws Exception {
        FileReaderServiceExecutor fileReaderServiceExecutor = new FileReaderServiceExecutor(executor, files);

        fileReaderServiceExecutor.readFile();

        verify(executor).awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
    }
}