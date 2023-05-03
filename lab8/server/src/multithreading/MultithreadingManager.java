package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadingManager {
    private static final ExecutorService requestThreadPool = Executors.newFixedThreadPool(4);
    private static final ExecutorService responseThreadPool = Executors.newCachedThreadPool();

    public static ExecutorService getRequestThreadPool() {
        return requestThreadPool;
    }

    public static ExecutorService getResponseThreadPool() {
        return responseThreadPool;
    }
}
