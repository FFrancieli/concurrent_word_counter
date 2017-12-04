package wordCounter;

import word.Word;

import java.util.List;
import java.util.concurrent.Callable;

public class WordCountTask implements Callable {
    private WordCounter wordCounter;
    private List<String> firstWordList;
    private List<String> secondWordList;

    public WordCountTask(List<String> firstWordList, List<String> secondWordList) {
        this.firstWordList = firstWordList;
        this.secondWordList = secondWordList;
        this.wordCounter = new WordCounter(firstWordList, secondWordList);
    }

    public WordCountTask() {
    }

    WordCountTask(List<String> firstWordList, List<String> secondWordList, WordCounter wordCounter) {
        this.firstWordList = firstWordList;
        this.secondWordList = secondWordList;
        this.wordCounter = wordCounter;
    }

    @Override
    public List<Word> call() throws Exception {
        return wordCounter.countWords();
    }

    public boolean canBeExecuted() {
        return firstWordList != null && secondWordList != null;
    }
}
