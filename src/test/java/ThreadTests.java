import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import threads.pool.ThreadPool;
import validators.RowValidator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreadTests {

    @Test
    @DisplayName("adds thread to pool")
    public void addThreadToPool(){
        Thread testThread = new Thread(new RowValidator());
        ThreadPool pool = ThreadPool.getThreadPoolInstance();
        pool.addThreadToPool(testThread);
        assertEquals(1,
                pool.getThreadsPoolSize(),
                "pool's size should equal " + Integer.toString(1));
    }
}
