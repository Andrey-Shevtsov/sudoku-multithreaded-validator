package threads.pool;

import java.util.List;

public class ThreadPool {
    private ThreadPool threadPoolInstance;
    private List<Thread> threads;

    public ThreadPool getThreadPoolInstance() {
        if (this.threadPoolInstance == null) {
            this.threadPoolInstance = new ThreadPool();
        }
        return this.threadPoolInstance;
    }

    public List<Thread> getThreads() {
        return this.threads;
    }
}
