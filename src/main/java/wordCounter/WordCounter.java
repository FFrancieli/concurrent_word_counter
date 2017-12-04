package wordCounter;

import word.Word;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCounter {

    private final List<String> firstWordList;
    private final List<String> secondWordList;

    public WordCounter(List<String> firstWordList, List<String> secondWordList) {
        this.firstWordList = firstWordList;
        this.secondWordList = secondWordList;
    }

    public List<Word> countWords() {
        List<String> mergedWordsList = mergeLists(firstWordList, secondWordList);

        Map<String, Integer> firstWordListFrequency = count(firstWordList);
        Map<String, Integer> secondWordListFrequency = count(secondWordList);

        return mergedWordsList.stream().map(word -> buildWordObject(firstWordListFrequency, secondWordListFrequency, word))
                .collect(Collectors.toList());
    }

    private List<String> mergeLists(List<String> firsList, List<String> secondList) {
        return Stream.concat(firsList.stream(), secondList.stream()).distinct().collect(Collectors.toList());
    }

    private Word buildWordObject(Map<String, Integer> firstWordListFrequency, Map<String, Integer> secondWordListFrequency, String word) {
        return new Word(word, firstWordListFrequency.getOrDefault(word, 0), secondWordListFrequency.getOrDefault(word, 0));
    }

    private Map<String, Integer> count(List<String> words) {
        return words.stream()
                .collect(Collectors
                        .toMap(word -> word, word -> 1, Integer::sum));
    }
}
