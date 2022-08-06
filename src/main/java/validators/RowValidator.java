package validators;

import java.util.Arrays;
import java.util.List;

public class RowValidator implements Runnable{

    private int[] row;
    private List<Integer> result; // refers to the list of results in Solution class

    public void setRow(int[] row) {
        this.row = row;
    }

    public int[] getRow() {
        return row;
    }

    public RowValidator(int[] row, List<Integer> result) {
        this.row = row;
        this.result = result;
    }

    public void sort() {
        for (int i = 1; i < row.length; i++) {
            int key = row[i];
            int j = i - 1;
            while (j >= 0 && row[j] > key) {
                row[j + 1] = row[j];
                j = j - 1;
            }
            row[j + 1] = key;
        }
    }

    public synchronized void checkRow() {
        sort();
        if (Arrays.toString(row).equals("[1, 2, 3, 4, 5, 6, 7, 8, 9]")) {
            this.result.add(1);
        } else this.result.add(0);
    }

    @Override
    public void run() {
        checkRow();
    }
}
