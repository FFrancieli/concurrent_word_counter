package wordCounter;

import cache.Cache;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import word.Word;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

        wordCounterService = new WordCounterService(executorService, wordCountTask);
        when(executorService.submit(any(Callable.class))).thenReturn(mock(Future.class));
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

    @Test
    public void returnWordFrequencyList() throws Exception {
        Word word = new Word("word", 1, 0);
        List<Word> words = Collections.singletonList(word);

        when(wordCountTask.call()).thenReturn(words);

        Cache cache = new Cache();
        cache.cache("a", Arrays.asList("a", "b"));
        cache.cache("g", Arrays.asList("a", "b"));

        WordCounterService wordCounterService = new WordCounterService(cache);
        wordCounterService.setWordCountTask(wordCountTask);

        List<Word> wordFrequency = wordCounterService.countWordsFrequency();

        assertThat(wordFrequency, is(words));
    }
}