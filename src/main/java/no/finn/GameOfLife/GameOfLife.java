package no.finn.GameOfLife;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: javalons
 * Date: 03/04/13
 * Time: 09:19
 * To change this template use File | Settings | File Templates.
 */
public class GameOfLife implements TableModel {

    private int[][] board;

    public GameOfLife(int pSize) {
        board = new int[pSize][pSize];
    }

    public GameOfLife(String file) {
        loadFromFile(file);
    }

    public String printCells() {
        String output = "";

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                output += board[row][col];
            }
            output += "\n";
        }

        return output;
    }

    public void setCellAlive(int row, int col) {
        board[row][col] = 1;
    }

    private int neighbours(int row, int col) {
        int counter = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                try {
                    if (board[i][j] == 1 && (i != row || j != col)) {
                        counter++;
                    }
                } catch ( ArrayIndexOutOfBoundsException ignored) {

                }
            }
        }

        return counter;
    }

    public void nextGeneration() {
        int size = board.length;
        int[][] tempBoard = new int[size][size];
        int n;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                n = neighbours(row, col);

                if (n > 3  ||  n < 2)
                    tempBoard[row][col] = 0;
                else if (n == 3)
                    tempBoard[row][col] = 1;
                else
                    tempBoard[row][col] = board[row][col];
            }
        }

        board = tempBoard;
    }

    private void loadFromFile(String path) {
        Path filePath = Paths.get(path);
        List<String> lines;
        int size;
        int actualLine = 0;

        try {
            lines = Files.readAllLines(filePath, Charset.defaultCharset());
            for (String line : lines) {
                //No check headers for correct file format
                if (line.substring(0, 2).equals("#P")) {
                    size = getSize(line);
                    board = new int[size][size];
                }

                if (line.substring(0, 1).equals(".") || line.substring(0, 1).equals("*")) {
                    for (int j = 0; j < line.length(); j++) {
                        if (line.charAt(j) == '*') {
                            board[actualLine][j] = 1;
                        }
                    }
                    actualLine++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private int getSize(String line) {
        String[] lineItems = line.split(" ");
        int size1 = Math.abs(Integer.parseInt(lineItems[1]));
        int size2 = Math.abs(Integer.parseInt(lineItems[2]));
        int size;

        if (size1 > size2) {
            size = Math.abs(size1) * 2 + 1;
        } else {
            size = Math.abs(size2) * 2 + 1;
        }

        return size;
    }

    @Override
    public int getRowCount() {
        return board.length;
    }

    @Override
    public int getColumnCount() {
        return board.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Integer.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return board[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if ((Integer)aValue == 1) {
            setCellAlive(rowIndex,columnIndex);
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
