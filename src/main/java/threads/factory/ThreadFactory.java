package threads.factory;

public class ThreadFactory {
    private ThreadFactory factoryInstance;

    public ThreadFactory getInstance() {
        if (this.factoryInstance == null) {
            this.factoryInstance = new ThreadFactory();
        }
        return this.factoryInstance;
    }

    private Thread createThread(Runnable obj) {
        return new Thread(obj);
    }
}
