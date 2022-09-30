/**
 * This class is for generating a table for word counter when provided with
 * column, row and size details.
 */
public class WordCounterTable {
    /**
     * This method is to insert border to the table cells.
     * @param leftCellSize is the size of left cell in the table for dynamic sizing.
     * @param rightCellSize is the size of right cell in the table for dynamic sizing.
     */
    public void insertBorder(int leftCellSize, int rightCellSize) {
        System.out.print("|");
        for (int i = 0; i <= leftCellSize + 1; i++) {
            System.out.print("-");
        }
        System.out.print("|");
        for (int j = 0; j <= rightCellSize + 1; j++) {
            System.out.print("-");
        }
        System.out.print("|");
        System.out.println();
    }
    /**
     * This method checks if a number is parsed to a String type.
     * @param cellName is the value of the string that could be a number
     * @return true if the String conceals the value of a number
     */
    private boolean isCellNameNumber(String cellName) {
        try {
            Double.parseDouble(cellName);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    /**
     * This is a space aligner for cells that hold letters (non-numerical values).
     * @param stringSize the number of characters in the string
     * @param cellSize the length of the cell in the table
     */
    private void createCellForString(int stringSize, int cellSize) {
        for (int j = stringSize; j <= cellSize; j++) {
            System.out.print(" ");
        }
    }
    /**
     * This is a space aligner for cells that hold numerical values in table.
     * Numbers should be right aligned.
     * @param numberSize the number of digits in the number
     * @param cellSize the length of the cell in the table
     */
    private void createCellForNumber(int numberSize, int cellSize) {
        for (int k = 1; k <= (cellSize - numberSize); k++) {
            System.out.print(" ");
        }
    }
    /**
     * This method is to generate a table row for word counter
     * with the word in one cell and its count in its adjacent cell.
     * @param cellNames names of cells in a row.
     * @param leftCellSize size of the cell comprising the word for dynamic adjustment.
     * @param rightCellSize size of the cell comprising the count for dynamic adjustment.
     */
    public void insertAlignedRow(String[] cellNames, int leftCellSize, int rightCellSize) {
        System.out.print("| ");
        System.out.print(cellNames[0]);

        createCellForString(cellNames[0].length(), leftCellSize);
        System.out.print("| ");
        //rightmost cell can either be a number or a string.
        if (isCellNameNumber(cellNames[1])) {
            createCellForNumber(cellNames[1].length(), rightCellSize);
            System.out.print(cellNames[1]);
        } else {
            System.out.print(cellNames[1]);
            createCellForString(cellNames[1].length(), rightCellSize - 1);
        }
        System.out.print(" |");
        System.out.println();
    }
    /**
     * This method is to insert a header or footer for Word Counter Table with only two columns.
     * @param cellNames names of cells forming the header or footer.
     * @param leftCellSize size of the leftmost cell for dynamic adjustment.
     * @param rightCellSize size of the rightmost cell for dynamic adjustment.
     * @throws Exception when the number of columns for the table is more than two.
     */
    public void insertHeaderOrFooter(String[] cellNames, int leftCellSize, int rightCellSize) throws Exception {
        int noOfColumns = cellNames.length;
        if (noOfColumns > 2) {
            throw new Exception("Sorry The Word Count Table is intended for a maximum of 2 columns");
        }
        insertBorder(leftCellSize, rightCellSize);
        insertAlignedRow(cellNames, leftCellSize, rightCellSize);
        insertBorder(leftCellSize, rightCellSize);
    }
}
