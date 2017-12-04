package wordCounter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import word.Word;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class WordCountTaskTest {

    public static final List<String> WORD_LIST = Collections.singletonList("word");

    @Mock
    WordCounter wordCounter;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void taskCannotBeExecutedWhenFirstWordListIsNull() throws Exception {
        WordCountTask task = new WordCountTask(null, WORD_LIST);

        assertThat(task.canBeExecuted(), is(false));
    }

    @Test
    public void taskCannotBeExecutedWhenSecondWordListIsNull() throws Exception {
        WordCountTask task = new WordCountTask(WORD_LIST, null);

        assertThat(task.canBeExecuted(), is(false));
    }

    @Test
    public void taskCannotBeExecutedWhenBothWordListsAreNull() throws Exception {
        WordCountTask task = new WordCountTask(null, null);

        assertThat(task.canBeExecuted(), is(false));
    }

    @Test
    public void taskCanBeExecutedWhenBothWordListAreFilled() throws Exception {
        WordCountTask task = new WordCountTask(WORD_LIST, WORD_LIST);

        assertThat(task.canBeExecuted(), is(true));
    }

    @Test
    public void countsWordFrequency() throws Exception {
        WordCountTask wordCountTask = new WordCountTask(WORD_LIST, WORD_LIST, wordCounter);

        wordCountTask.call();

        verify(wordCounter).countWords();
    }

    @Test
    public void returnsWordFrequency() throws Exception {
        List<Word> words = Collections.singletonList(new Word("word", 1, 1));
        when(wordCounter.countWords()).thenReturn(words);

        WordCountTask wordCountTask = new WordCountTask(WORD_LIST, WORD_LIST, wordCounter);

        List<Word> wordFrequency = wordCountTask.call();

        assertThat(wordFrequency, is(words));
    }
}