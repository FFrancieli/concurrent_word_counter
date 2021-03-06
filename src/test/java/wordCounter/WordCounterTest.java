package wordCounter;

import org.junit.Test;
import word.Word;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WordCounterTest {

    @Test
    public void returnsEmptyListOfWordsWhenBothWordListsAreEmpty() throws Exception {
        WordCounter wordCounter = new WordCounter(Collections.emptyList(), Collections.emptyList());

        List<Word> wordFrequency = wordCounter.countWords();

        assertThat(wordFrequency.size(), is(0));
    }

    @Test
    public void countsSingleWordOnFirstList() throws Exception {
        List<String> firstWordList = Arrays.asList("word");
        WordCounter wordCounter = new WordCounter(firstWordList, Collections.emptyList());

        List<Word> wordFrequency = wordCounter.countWords();

        assertThat(wordFrequency.size(), is(1));

        Word word = wordFrequency.get(0);
        assertThat(wordFrequency.size(), is(1));
        assertThat(word.getFrequencyFirstFile(), is(1));
        assertThat(word.getFrequencySecondFile(), is(0));
    }

    @Test
    public void countsDifferentWordsPresentOnFirstWordListOnly() throws Exception {
        List<String> firstWordList = Arrays.asList("word", "hello");
        WordCounter wordCounter = new WordCounter(firstWordList, Collections.emptyList());

        List<Word> wordFrequency = wordCounter.countWords();

        assertThat(wordFrequency.size(), is(2));

        Word word = wordFrequency.get(0);
        assertThat(word.getFrequencyFirstFile(), is(1));
        assertThat(word.getFrequencySecondFile(), is(0));

        Word hello = wordFrequency.get(1);
        assertThat(hello.getFrequencyFirstFile(), is(1));
        assertThat(hello.getFrequencySecondFile(), is(0));
    }

    @Test
    public void countsMultipleOccurrencesOfSameWord() throws Exception {
        List<String> firstWordList = Arrays.asList("word", "hello", "hi", "hello");
        WordCounter wordCounter = new WordCounter(firstWordList, Collections.emptyList());
        List<Word> wordFrequency = wordCounter.countWords();

        assertThat(wordFrequency.size(), is(3));

        Word word = wordFrequency.get(0);
        assertThat(word.getFrequencyFirstFile(), is(1));
        assertThat(word.getFrequencySecondFile(), is(0));

        Word hello = wordFrequency.get(1);
        assertThat(hello.getFrequencyFirstFile(), is(2));
        assertThat(hello.getFrequencySecondFile(), is(0));

        Word hi = wordFrequency.get(2);
        assertThat(hi.getFrequencyFirstFile(), is(1));
        assertThat(hi.getFrequencySecondFile(), is(0));
    }
    
    @Test
    public void countsSingleWordOnSecondWordList() throws Exception {
        List<String> secondWordList = Arrays.asList("word");
        WordCounter wordCounter = new WordCounter(Collections.emptyList(), secondWordList);

        List<Word> wordFrequency = wordCounter.countWords();

        assertThat(wordFrequency.size(), is(1));

        Word word = wordFrequency.get(0);
        assertThat(wordFrequency.size(), is(1));
        assertThat(word.getFrequencyFirstFile(), is(0));
        assertThat(word.getFrequencySecondFile(), is(1));
    }

    @Test
    public void countsDifferentWordsPresentOnSecondWordListOnly() throws Exception {
        List<String> secondWordList = Arrays.asList("word", "hello");
        WordCounter wordCounter = new WordCounter(Collections.emptyList(), secondWordList);

        List<Word> wordFrequency = wordCounter.countWords();

        assertThat(wordFrequency.size(), is(2));

        Word word = wordFrequency.get(0);
        assertThat(word.getFrequencyFirstFile(), is(0));
        assertThat(word.getFrequencySecondFile(), is(1));

        Word hello = wordFrequency.get(1);
        assertThat(hello.getFrequencyFirstFile(), is(0));
        assertThat(hello.getFrequencySecondFile(), is(1));
    }

    @Test
    public void countsWordsPresentOnBothLists() throws Exception {
        List<String> firstList = Arrays.asList("hello", "world");
        List<String> secondWordList = Arrays.asList("word", "hello");
        WordCounter wordCounter = new WordCounter(firstList, secondWordList);

        List<Word> wordFrequency = wordCounter.countWords();

        assertThat(wordFrequency.size(), is(3));

        Word hello = wordFrequency.get(0);
        assertThat(hello.getFrequencyFirstFile(), is(1));
        assertThat(hello.getFrequencySecondFile(), is(1));

        Word world = wordFrequency.get(1);
        assertThat(world.getFrequencyFirstFile(), is(1));
        assertThat(world.getFrequencySecondFile(), is(0));

        Word word = wordFrequency.get(2);
        assertThat(word.getFrequencyFirstFile(), is(0));
        assertThat(word.getFrequencySecondFile(), is(1));
    }
}