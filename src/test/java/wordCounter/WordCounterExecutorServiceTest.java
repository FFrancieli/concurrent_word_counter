package wordCounter;

import org.junit.Test;
import tasks.FileReaderTask;
import tasks.WordCountTask;

import java.io.File;
import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.*;

public class WordCounterExecutorServiceTest {

    @Test
    public void executesReadFileTask() throws Exception {
        ExecutorService executor = mock(ExecutorService.class);
        File firstFile = new File("some/file/path");
        File secondFile = new File("another/file/path");

        WordCounterExecutorService wordCounterservice = new WordCounterExecutorService(executor, firstFile, secondFile);
        wordCounterservice.countWordFrequencyFromFiles();

        verify(executor, times(2)).submit(any(FileReaderTask.class));
    }

    @Test
    public void executesWordCountTask() throws Exception {
        ExecutorService executor = mock(ExecutorService.class);
        File firstFile = new File("some/file/path");
        File secondFile = new File("another/file/path");

        WordCounterExecutorService wordCounterservice = new WordCounterExecutorService(executor, firstFile, secondFile);
        wordCounterservice.countWordFrequencyFromFiles();

        verify(executor).submit(any(WordCountTask.class));
    }

    @Test
    public void shutsDownTaskExecutor() throws Exception {
        ExecutorService executor = mock(ExecutorService.class);
        File firstFile = new File("some/file/path");
        File secondFile = new File("another/file/path");

        WordCounterExecutorService wordCounterservice = new WordCounterExecutorService(executor, firstFile, secondFile);
        wordCounterservice.countWordFrequencyFromFiles();

        verify(executor).shutdown();
    }
}