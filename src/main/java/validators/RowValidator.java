package validators;

import java.util.Arrays;

public class RowValidator implements Runnable{

    private int[] row;
    private volatile int result;

    public void setRow(int[] row) {
        this.row = row;
    }

    public int[] getRow() {
        return row;
    }

    public int getResult() {
        return result;
    }

    public void sort() {
        for (int j = 0; j < row.length; j++) {
            int key = row[j];
            int i = j - 1;
            while (i > 0 && row[i] > key) {
                row[i + 1] = row[i];
                i--;
            }
            row[i + 1] = key;
        }
    }

    @Override
    public void run() {
        sort();
        if (Arrays.toString(row).equals("123456789")) {
            this.result = 1;
        } else this.result = 0;
    }
}
