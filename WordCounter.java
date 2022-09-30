import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Scanner;
/**
 * A class to print the count of words when asked
 * to search for, in a file.
 */
public class WordCounter {
    static final String TOTAL = "TOTAL"; //A constant representing a key for wordCount map.
    static final String WORD = "WORD"; //A constant representing a header value.
    static final String COUNT = "COUNT"; //A constant representing a header value.
    /**
     * This method is intended to extract the filename and the words that have to be searched
     * when input via command line.
     * If the necessary conditions for input are not met, an exception is thrown.
     *
     * @param args Command line arguments comprising filename and words to count in the
     *             same order
     * @return WordCountInputPayload instance that represents fileName and an array of words
     * to be counted
     */
    private static WordCountInputPayload processInputs(String[] args) {
        int inputLength = args.length;
        String fileName = args.length > 1 ? args[0] : "";
        int minArgsLength = 2;

        if (inputLength < minArgsLength || fileName.trim().length() < 1) {
            throw new IllegalArgumentException();
        }
        return new WordCountInputPayload(args);
    }
    /**
     * This method is for initialising a map comprising words and their counts set to zero.
     *
     * @param wordsToCount is an array holds the words to be counted.
     * @return a map of words and their counts initialised to zero.
     */
    private static LinkedHashMap<String, Integer> createWordCountMap(String[] wordsToCount) {
        LinkedHashMap<String, Integer> wordCountMap = new LinkedHashMap<>();
        for (String word : wordsToCount) {
            wordCountMap.put(word, 0);
        }
        return wordCountMap;
    }
    /**
     * This method browses through the file to be searched, compares and increments count for each matched words with
     * the given set of words of interest.
     *
     * @param wordCountMap  a map of words of interest, count set to zero.
     * @param fileName      name of the file to be browsed.
     * @param fileDelimiter a regex for Java Scanner to separate words.
     * @throws FileNotFoundException thrown when the file to search for does not exist
     */
    private static void computeWordCount(LinkedHashMap<String, Integer> wordCountMap, String fileName, String fileDelimiter) throws FileNotFoundException {
        try {
            File fileObj = new File(fileName);
            Scanner textScanner = new Scanner(fileObj, StandardCharsets.UTF_8).useDelimiter(fileDelimiter);
            while (textScanner.hasNext()) {
                String nextWord = textScanner.next();
                if (wordCountMap.containsKey(nextWord)) {
                    int count = wordCountMap.get(nextWord) + 1;
                    wordCountMap.put(nextWord, count);
                }
            }
            textScanner.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * This method generates a report for word count in a tabular format for multiple words search.
     *
     * @param wordCountMap a map of words of interest and their corresponding counts.
     * @throws Exception when the number of columns are more than two.
     */
    private static void printTabularReport(LinkedHashMap<String, Integer> wordCountMap) throws Exception {
        WordCounterTable table = new WordCounterTable();

        String[] headerNames = new String[]{WORD, COUNT};
        String footerName = TOTAL;

        int maxLeftCellSize = footerName.length();
        int maxRightCellSize = headerNames[1].length();

        int sum = 0;
        //Computes the cell size for tables, also
        for (String key : wordCountMap.keySet()) {
            if (key.length() > maxLeftCellSize) {
                maxLeftCellSize = key.length();
            }
            int val = wordCountMap.get(key);
            sum += val;
            if (String.valueOf(val).length() > maxRightCellSize) {
                maxRightCellSize = String.valueOf(wordCountMap.get(key)).length();
            }
        }

        table.insertHeaderOrFooter(headerNames, maxLeftCellSize, maxRightCellSize);

        for (String word : wordCountMap.keySet()) {
            int val = wordCountMap.get(word);
            String[] cellNames = new String[]{word, String.valueOf(val)};

            table.insertAlignedRow(cellNames, maxLeftCellSize, maxRightCellSize);
        }

        String[] footerNames = new String[]{footerName, String.valueOf(sum)};
        table.insertHeaderOrFooter(footerNames, maxLeftCellSize, maxRightCellSize);
    }
    /**
     * This method generates a report for word count in a tabular format for multiple word searches
     * or a single statement for single word search.
     *
     * @param wordCountMap a map of words of interest and their corresponding counts.
     * @param wordsToCount an array of words to be searched.
     * @throws Exception when the number of columns are more than two for tabular report.
     */
    private static void printWordCount(LinkedHashMap<String, Integer> wordCountMap, String[] wordsToCount) throws Exception {
        if (wordCountMap.size() == 1) {
            int count = wordCountMap.get(wordsToCount[0]);
            String suffix = count == 1 ? " time." : " times.";
            System.out.print(("The word '" + wordsToCount[0] + "' appears " + count + suffix));
        } else {
            printTabularReport(wordCountMap);
        }
    }
    /**
     * This method accepts inputs for filename and the words to be searched and counted
     * via command line arguments.
     *
     * @param args comprise an array of filename and words to count in the same order.
     */
    public static void main(String[] args) {
        try {
            WordCountInputPayload payload = processInputs(args);
            LinkedHashMap<String, Integer> wordCountMap = createWordCountMap(payload.getWordsToCount());
            //File delimiter for java scanner - corresponds to [^a-zA-Z0-9_]
            String fileDelimiter = "\\W";
            computeWordCount(wordCountMap, payload.getFileName(), fileDelimiter);
            printWordCount(wordCountMap, payload.getWordsToCount());
        } catch (IllegalArgumentException e) {
            System.out.print("Usage: java WordCounter <filename> <searchTerm>");
        } catch (FileNotFoundException e) {
            System.out.print("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.print("Sorry, something went wrong." + e);
        }
    }
}
