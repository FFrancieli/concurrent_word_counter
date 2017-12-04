package wordCounter;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WordCountTaskTest {

    public static final List<String> WORD_LIST = Collections.singletonList("word");

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
}