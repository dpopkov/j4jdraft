package ru.j4jdraft.mt.stopconsumer;

import ru.j4jdraft.mt.producerconsumer.BoundedBlockingQueue;

import java.util.concurrent.TimeUnit;

public class StopConsumer {
    public static void main(String[] args) throws InterruptedException {
        consumerStopsByInterrupting();

//        consumerStopsAfterProducerIsNotAlive(); // -- not reliable solution
    }

    private static void consumerStopsByInterrupting() throws InterruptedException {
        BoundedBlockingQueue<Integer> queue = new BoundedBlockingQueue<>(3);
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 7; i++) {
                queue.put(i);
                System.out.println("Produced: " + i);
            }
        }, "Producer-Thread");
        Thread consumer = new Thread(() -> {
            while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                Integer value = queue.take();
                System.out.println("\t\t\tConsumed: " + value);
            }
        }, "Consumer-Thread");
        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();
    }

    /**
     * In this solution the Consumer checks whether the Producer is alive.
     * This solution is not reliable because the Producer may stop after check but before
     * invoking <code>take()</code> on the queue, then consuming thread is blocked.
     */
    @SuppressWarnings("unused")
    private static void consumerStopsAfterProducerIsNotAlive() {
        BoundedBlockingQueue<Integer> queue = new BoundedBlockingQueue<>(3);
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                queue.put(i);
                System.out.println("Produced: " + i);
            }
        }, "Producer-Thread");
        Thread consumer = new Thread(() -> {
            while (true) {
                if (queue.isEmpty()) {
                    System.out.println("Queue is empty - need to check Producer");
                    if (!producer.isAlive()) {
                        System.out.println("Producer is not alive - Consumer stops");
                        break;
                    }
                }
                Integer value = queue.take();
                System.out.println("\t\t\tConsumed: " + value);
            }
        }, "Consumer-Thread");
        producer.start();
        consumer.start();
    }
}
