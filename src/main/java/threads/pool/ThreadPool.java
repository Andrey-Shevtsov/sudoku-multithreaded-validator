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

    public Object executeThread(int id) {
        threads.get(id).start();
        return threads.get(id);
    }

    public void executeAllThreads() {
        threads.forEach(Thread::start);
    }

    public void joinThread(int id) throws InterruptedException {
        threads.get(id).join();
    }

    public void deleteThread(int id) {
        threads.remove(id);
    }
}
