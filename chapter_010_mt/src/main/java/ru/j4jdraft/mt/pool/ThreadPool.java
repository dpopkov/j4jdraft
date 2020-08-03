package ru.j4jdraft.mt.pool;

import ru.j4jdraft.mt.producerconsumer.BoundedBlockingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;

public class ThreadPool {
    private final List<Worker> workers;
    private final BoundedBlockingQueue<Runnable> queue;
    private volatile boolean canAcceptNewTasks = true;

    public ThreadPool(int queueCapacity) {
        queue = new BoundedBlockingQueue<>(queueCapacity);
        workers = initWorkers(Runtime.getRuntime().availableProcessors());
    }

    private List<Worker> initWorkers(int numThreads) {
        List<Worker> workers = new ArrayList<>(numThreads);
        for (int i = 0; i < numThreads; i++) {
            workers.add(new Worker(i));
        }
        workers.forEach(Thread::start);
        return workers;
    }

    public void execute(Runnable task) throws InterruptedException {
        if (!canAcceptNewTasks) {
            throw new RejectedExecutionException();
        }
        queue.put(task);
    }

    public void shutdown() {
        canAcceptNewTasks = false;
    }

    public void shutdownNow() {
        shutdown();
        workers.forEach(Thread::interrupt);
    }

    public void awaitTermination() throws InterruptedException {
        for (Worker worker : workers) {
            if (worker.isIdle()) {
                worker.interrupt();
            } else {
                worker.join();
            }
        }
    }

    private class Worker extends Thread {
        private volatile boolean idle = true;

        public Worker(int id) {
            super("thread-pool-" + id);
        }

        @Override
        public void run() {
            while (canAcceptNewTasks && !Thread.interrupted()) {
                try {
                    Runnable task = queue.take();
                    idle = false;
                    task.run();
                    idle = true;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        public boolean isIdle() {
            return idle;
        }
    }
}
