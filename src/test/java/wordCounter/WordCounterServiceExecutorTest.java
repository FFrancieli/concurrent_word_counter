package wordCounter;

import cache.Cache;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import word.Word;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class WordCounterServiceExecutorTest {

    @Mock
    ExecutorService executorService;

    @Mock
    Cache cache;

    @Mock
    WordCountTask wordCountTask;

    WordCounterServiceExecutor wordCounterServiceExecutor;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        wordCounterServiceExecutor = new WordCounterServiceExecutor(executorService, wordCountTask);
        when(wordCountTask.canBeExecuted()).thenReturn(true);

        Word word = new Word("word", 1, 0);
        List<Word> words = Collections.singletonList(word);

        Future future = mock(Future.class);
        when(future.get()).thenReturn(words);

        when(executorService.submit(any(WordCountTask.class))).thenReturn(future);

    }

    @Test
    public void executesWordCountTask() throws Exception {
        wordCounterServiceExecutor.countWordsFrequency();

        verify(executorService).submit(wordCountTask);
    }

    @Test
    public void shutsDownExecutorService() throws Exception {
        wordCounterServiceExecutor.countWordsFrequency();

        verify(executorService).shutdown();
    }

    @Test
    public void getsCacheContentAsListOnInitialize() throws Exception {
        new WordCounterServiceExecutor(cache);

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

        WordCounterServiceExecutor wordCounterServiceExecutor = new WordCounterServiceExecutor(cache);
        wordCounterServiceExecutor.setWordCountTask(wordCountTask);

        List<Word> wordFrequency = wordCounterServiceExecutor.countWordsFrequency();

        assertThat(wordFrequency, is(words));
    }

    @Test
    public void doesNotExecuteWordCountTaskWhenTaskCannotBeExecuted() throws Exception {
        when(wordCountTask.canBeExecuted()).thenReturn(false);

        wordCounterServiceExecutor.countWordsFrequency();

        verify(executorService, never()).submit(any(WordCountTask.class));
    }

    @Test
    public void returnsEmptyWordListWhenWordCountTaskCannotBeExecuted() throws Exception {
        when(wordCountTask.canBeExecuted()).thenReturn(false);

        List<Word> wordsFrequency = wordCounterServiceExecutor.countWordsFrequency();

        assertThat(wordsFrequency.isEmpty(), is(true));
    }

    @Test
    public void sortsWordList() throws Exception {
        Word word = new Word("word", 1, 2);
        Word anotherWord = new Word("hello", 1, 1);
        List<Word> words = Arrays.asList(anotherWord, word);

        when(wordCountTask.call()).thenReturn(words);

        WordCounterServiceExecutor wordCounterServiceExecutor = new WordCounterServiceExecutor(cache);
        wordCounterServiceExecutor.setWordCountTask(wordCountTask);

        List<Word> wordFrequency = wordCounterServiceExecutor.countWordsFrequency();

        assertThat(wordFrequency.get(0).getWord(), is(word.getWord()));
        assertThat(wordFrequency.get(1).getWord(), is(anotherWord.getWord()));
    }
}