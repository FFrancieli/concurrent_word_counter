package wordCounter;

import cache.Cache;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.concurrent.ExecutorService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class WordCounterServiceTest {

    @Mock
    ExecutorService executorService;

    @Mock
    Cache cache;

    @Mock
    WordCountTask wordCountTask;

    WordCounterService wordCounterService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        wordCounterService = new WordCounterService(executorService , wordCountTask);
    }

    @Test
    public void executesWordCountTask() throws Exception {
        wordCounterService.countWordsFrequency();

        verify(executorService).submit(any(WordCountTask.class));
    }

    @Test
    public void shutsDownExecutorService() throws Exception {
        wordCounterService.countWordsFrequency();

        verify(executorService).shutdown();
    }

    @Test
    public void getsCacheContentAsListOnInitialize() throws Exception {
        new WordCounterService(cache);

        verify(cache).asList();
    }
}