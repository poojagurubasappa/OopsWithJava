import java.util.Arrays;
import java.util.LinkedHashSet;
/**
 * This class holds the fileName and the words to be counted
 * extracted from input arguments.
 */
public class WordCountInputPayload {
    private String fileName;
    private String[] wordsToCount;
    WordCountInputPayload(String[] args) {
        fileName = args.length > 0 ? args[0] : "";
        String[] searchStrings = new String[args.length - 1];
        for (int i = 1; i < args.length; i++) {
           searchStrings[i - 1] = args[i];
        }

        LinkedHashSet<String> uniqueWordsSet = new LinkedHashSet<>(Arrays.asList(searchStrings));
        wordsToCount = uniqueWordsSet.toArray(new String[uniqueWordsSet.size()]);
    }
    /**
     * Getter for filename.
     * @return name of the file
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Getter for words to count array.
     * @return an array of words to count.
     */
    public String[] getWordsToCount() {
        return wordsToCount;
    }
}
