import threads.factory.ThreadFactory;
import threads.pool.ThreadPool;
import validators.ColumnValidator;
import validators.RowValidator;
import validators.TileValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {

    private final ThreadFactory factory = ThreadFactory.getInstance();
    private final ThreadPool pool = ThreadPool.getThreadPoolInstance();

    // predefined rows columns and tiles
    // rows
    int[][] rows = {
            {6, 2, 4, 5, 3, 9, 1, 8, 7},
            {5, 1, 9, 7, 2, 8, 6, 3, 4},
            {8, 3, 7, 6, 1, 4, 2, 9, 5},
            {1, 4, 3, 8, 6, 5, 7, 2, 9},
            {9, 5, 8, 2, 4, 7, 3, 6, 1},
            {7, 6, 2, 3, 9, 1, 4, 5, 8},
            {3, 7, 1, 9, 5, 6, 8, 4, 2},
            {4, 9, 6, 1, 8, 2, 5, 7, 3},
            {2, 8, 5, 4, 7, 3, 9, 1, 6}
    };

    // columns
    int[][] cols = {
            {6, 5, 8, 1, 9, 7, 3, 4, 2},
            {2, 1, 3, 4, 5, 6, 7, 9, 8},
            {4, 9, 7, 3, 8, 2, 1, 6, 5},
            {5, 7, 6, 8, 2, 3, 9, 1, 4},
            {3, 2, 1, 6, 4, 9, 5, 8, 7},
            {9, 8, 4, 5, 7, 1, 6, 2, 3},
            {1, 6, 2, 7, 3, 4, 8, 5, 9},
            {8, 3, 9, 2, 6, 5, 4, 7, 1},
            {7, 4, 5, 9, 1, 8, 2, 3, 6}
    };

    // tiles
    int[][][] tiles = {
            {{6, 2, 4}, {5, 1, 9}, {8, 3, 7}},
            {{5, 3, 9}, {7, 2, 8}, {6, 1, 4}},
            {{1, 8, 7}, {6, 3, 4}, {2, 9, 5}},
            {{1, 4, 3}, {9, 5, 8}, {7, 6, 2}},
            {{8, 6, 5}, {2, 4, 7}, {3, 9, 1}},
            {{7, 2, 9}, {3, 6, 1}, {4, 5, 8}},
            {{3, 7, 1}, {4, 9, 6}, {2, 8, 5}},
            {{9, 5, 6}, {1, 8, 2}, {4, 7, 3}},
            {{8, 4, 2}, {5, 7, 3}, {9, 1, 6}}
    };

    public static volatile List<Integer> results = new ArrayList<>(27);
    public int validate(String[] args) throws NoSuchFieldException, InterruptedException {

        AtomicInteger answer = new AtomicInteger(1);
        // creating treads for row validating and assigning them to pool

        for (int i = 0; i < 9; i++) {
            pool.addThreadToPool(factory.createThread(new RowValidator(rows[i], results)));
        }

        // creating threads for column validating and assigning them to pool

        for (int i = 0; i < 9; i++) {
            pool.addThreadToPool(factory.createThread(new ColumnValidator(cols[i], results)));
        }

        // creating threads for tile validating and assigning them to pool

        for (int i = 0; i < 9; i++) {
            pool.addThreadToPool(factory.createThread(new TileValidator(tiles[i], results)));
        }

        // running validation

        pool.executeAllThreads();

        for (int i = 0; i < 27; i++) {
            pool.joinThread(i);
        }

        // checking results list for answers from all threads;

        results.forEach(integer -> {
            if (integer == 0) answer.set(0);
        });
        return answer.get();
    }
}
