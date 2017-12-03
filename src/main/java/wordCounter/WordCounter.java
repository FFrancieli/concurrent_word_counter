package wordCounter;

import word.Words.Word;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCounter {

    private final List<String> firstWordList;
    private final List<String> secondWordList;

    public WordCounter(List<String> firstWordList, List<String> secondWordList) {
        this.firstWordList = toLowerCase(firstWordList);
        this.secondWordList = toLowerCase(secondWordList);
    }

    private List<String> toLowerCase(List<String> list) {
        return list.stream().map(String::toLowerCase).collect(Collectors.toList());
    }

    public List<Word> countWords() {
        List<String> mergedWordsList = Stream.concat(firstWordList.stream(), secondWordList.stream()).collect(Collectors.toList());

        Map<String, Integer> firstWordListFrequency = count(firstWordList);
        Map<String, Integer> secondWordListFrequency = count(secondWordList);

        return mergedWordsList.stream().distinct()
                .map(word -> buildWordObject(firstWordListFrequency, secondWordListFrequency, word))
                .collect(Collectors.toList());
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
