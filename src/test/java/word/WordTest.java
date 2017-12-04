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

    @Test
    public void parsesWordToString() throws Exception {
        Word word = new Word("hello", 2, 1);

        String expectedString = word.getWord()  + " " + word.getTotalFrequency() + " " + "= " + word.getFrequencyFirstFile() + " + " + word.getFrequencySecondFile();

        assertThat(word.toString(), is(expectedString));
    }
}