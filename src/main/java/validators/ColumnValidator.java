package validators;

import java.util.Arrays;
import java.util.List;

public class ColumnValidator implements Runnable{

    private int[] column;
    private List<Integer> result;

    public void setColumn(int[] column) {
        this.column = column;
    }

    public int[] getColumn() {
        return column;
    }

    public ColumnValidator(int[] col, List<Integer> result) {
        this.column = col;
        this.result = result;
    }

    public void sort() {
        for (int i = 1; i < column.length; i++) {
            int key = column[i];
            int j = i - 1;
            while (j >= 0 && column[j] > key) {
                column[j + 1] = column[j];
                j = j - 1;
            }
            column[j + 1] = key;
        }
    }

    public synchronized void checkColumn() {
        sort();
        if (Arrays.toString(column).equals("[1, 2, 3, 4, 5, 6, 7, 8, 9]")) {
            this.result.add(1);
        } else this.result.add(0);
    }
    @Override
    public void run() {
        checkColumn();
    }
}
