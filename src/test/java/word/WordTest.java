package word;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class WordTest {

    @Test
    public void calculatesTheTotalFrequencyOfTheWord() throws Exception {
        Word word = new Word("hello", 2, 1);
        
        assertThat(word.getTotalFrequency(), is(3));
    }
}