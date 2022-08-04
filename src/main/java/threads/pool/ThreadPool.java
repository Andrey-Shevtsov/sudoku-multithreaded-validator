package threads.pool;

import java.util.ArrayList;
import java.util.List;

public final class ThreadPool {
    private static ThreadPool threadPoolInstance;
    private List<Thread> threads = new ArrayList<>();

    public static ThreadPool getThreadPoolInstance() {
        if (threadPoolInstance == null) {
            threadPoolInstance = new ThreadPool();
        }
        return threadPoolInstance;
    }

    public int getThreadsPoolSize() {
        return this.threads.size();
    }
    public void addThreadToPool(Thread t) {
        this.threads.add(t);
    }
}
