import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import threads.pool.ThreadPool;
import validators.RowValidator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreadTests {

    @Test
    @DisplayName("adds thread to pool")
    public void addThreadToPool(){
        List<Integer> result = new ArrayList<>(1);
        Thread testThread = new Thread(new RowValidator(new int[]{1, 3, 9, 2, 7, 6, 5, 4, 8}, result));
        ThreadPool pool = ThreadPool.getThreadPoolInstance();
        pool.addThreadToPool(testThread);
        assertEquals(1,
                pool.getThreadsPoolSize(),
                "pool's size should equal " + Integer.toString(1));
    }
}
