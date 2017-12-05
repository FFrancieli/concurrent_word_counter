package word;

public class Word implements Comparable<Word> {
    private String word;
    private int frequencyFirstFile;
    private int frequencySecondFile;
    private int totalFrequency;

    public Word(String word, int frequencyFirstFile, int frequencySecondFile) {
        this.word = word;
        this.frequencyFirstFile = frequencyFirstFile;
        this.frequencySecondFile = frequencySecondFile;
        this.totalFrequency = frequencyFirstFile + frequencySecondFile;
    }

    public int getFrequencyFirstFile() {
        return frequencyFirstFile;
    }

    public int getFrequencySecondFile() {
        return frequencySecondFile;
    }

    public int getTotalFrequency() {
        return totalFrequency;
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return word + " " + totalFrequency + " = " + frequencyFirstFile + " + " + frequencySecondFile;
    }

    @Override
    public int compareTo(Word anotherWord) {
        if (this.totalFrequency > anotherWord.totalFrequency) {
            return -1;
        } else if (this.totalFrequency < anotherWord.totalFrequency) {
            return 1;
        }

        return 0;
    }
}
