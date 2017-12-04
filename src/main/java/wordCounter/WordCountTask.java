package wordCounter;

import word.Word;

import java.util.List;
import java.util.concurrent.Callable;

public class WordCountTask implements Callable {
    private final List<String> firstWordList;
    private final List<String> secondWordList;

    public WordCountTask(List<String> firstWordList, List<String> secondWordList) {
        this.firstWordList = firstWordList;
        this.secondWordList = secondWordList;
    }

    @Override
    public List<Word> call() throws Exception {
        return null;
    }
}
