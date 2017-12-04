package wordCounter;

import word.Word;

import java.util.List;
import java.util.concurrent.Callable;

public class WordCountTask implements Callable {
    private List<String> firstWordList;
    private List<String> secondWordList;

    public WordCountTask(List<String> firstWordList, List<String> secondWordList) {
        this.firstWordList = firstWordList;
        this.secondWordList = secondWordList;
    }

    public WordCountTask() {
    }

    @Override
    public List<Word> call() throws Exception {
        return null;
    }

    public boolean canBeExecuted() {
        return firstWordList != null && secondWordList != null;
    }
}
