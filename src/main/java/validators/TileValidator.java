package validators;

import java.util.Arrays;
import java.util.List;

public class TileValidator implements Runnable{

    private int[][] tile;
    private volatile int[] converted;
    private List<Integer> result;

    public void setTile(int[][] tile) {
        this.tile = tile;
    }

    public TileValidator(int[][] tile, List<Integer> result) {
        this.tile = tile;
        this.result = result;
    }

    public int[] convertTile() {
        int[] converted = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                converted[counter] = this.tile[i][j];
                counter++;
            }
        }
        return converted;
    }

    public void sort() {
        for (int i = 1; i < converted.length; i++) {
            int key = converted[i];
            int j = i - 1;
            while (j >= 0 && converted[j] > key) {
                converted[j + 1] = converted[j];
                j = j - 1;
            }
            converted[j + 1] = key;
        }
    }

    public synchronized void checkTile() {
        converted = convertTile();
        sort();
        if (Arrays.toString(converted).equals("[1, 2, 3, 4, 5, 6, 7, 8, 9]")) {
            result.add(1);
        } else result.add(0);
    }
    @Override
    public void run() {
        checkTile();
    }
}
