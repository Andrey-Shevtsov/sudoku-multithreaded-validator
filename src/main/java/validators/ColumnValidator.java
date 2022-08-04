package validators;

import java.util.Arrays;

public class ColumnValidator implements Runnable{

    private int[] column;
    private volatile int result;

    public void setColumn(int[] column) {
        this.column = column;
    }

    public int[] getColumn() {
        return column;
    }

    public int getResult() {
        return result;
    }

    public void sort() {
        for (int j = 0; j < column.length; j++) {
            int key = column[j];
            int i = j - 1;
            while (i > 0 && column[i] > key) {
                column[i + 1] = column[i];
                i--;
            }
            column[i + 1] = key;
        }
    }

    @Override
    public void run() {
        sort();
        if (Arrays.toString(column).equals("123456789")) {
            this.result = 1;
        } else this.result = 0;
    }
}
