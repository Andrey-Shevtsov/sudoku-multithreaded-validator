package validators;

import java.util.Arrays;

public class TileValidator implements Runnable{

    private int[][] tile;
    private volatile int[] converted;
    private volatile int result;

    public void setTile(int[][] tile) {
        this.tile = tile;
    }

    public int getResult() {
        return result;
    }

    public int[] convertTile() {
        int[] converted = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                converted[j * (i + 1)] = this.tile[i][j];
            }
        }
        return converted;
    }

    public void sort() {
        for (int j = 0; j < converted.length; j++) {
            int key = converted[j];
            int i = j - 1;
            while (i > 0 && converted[i] > key) {
                converted[i + 1] = converted[i];
                i--;
            }
            converted[i + 1] = key;
        }
    }
    @Override
    public void run() {
        converted = convertTile();
        sort();
        if (Arrays.toString(converted).equals("123456789")) {
            result = 1;
        } else result = 0;
    }
}
