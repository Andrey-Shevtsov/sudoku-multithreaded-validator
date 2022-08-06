package threads.factory;

public final class ThreadFactory {
    private static ThreadFactory factoryInstance;

    public static ThreadFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new ThreadFactory();
        }
        return factoryInstance;
    }

    public Thread createThread(Runnable obj) {
        return new Thread(obj);
    }
}
