package concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer1 {
    private static final int MAX_THREAD_POOL_SIZE = 10;

    private class Producer implements Runnable {
        BlockingQueue<String> blockingQueue;
        CountDownLatch readLatch;
        public Producer(BlockingQueue<String> blockingQueue, CountDownLatch readLatch){
            this.blockingQueue = blockingQueue;
            this.readLatch = readLatch;
        }
        @Override
        public void run() {
            try {
                for(int i=0;i<10;i++) {
                    String currDate = String.valueOf(System.currentTimeMillis());
                    blockingQueue.add(currDate);
                    System.out.println("Producer: " + currDate);
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readLatch.countDown();
            }
        }
    }

    private class Consumer implements Runnable {
        BlockingQueue<String> blockingQueue;
        CountDownLatch readLatch;
        CountDownLatch writeLatch;
        public Consumer(BlockingQueue<String> blockingQueue, CountDownLatch readLatch, CountDownLatch writeLatch){
            this.blockingQueue = blockingQueue;
            this.readLatch = readLatch;
            this.writeLatch = writeLatch;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": starting consumer run");
            try {
                boolean isCurrThreadInterrupted = Thread.currentThread().isInterrupted();
                boolean hasMoreItemsToRead = readLatch.getCount() > 0;
                boolean queueHasData = !blockingQueue.isEmpty();
                while (!isCurrThreadInterrupted && hasMoreItemsToRead && queueHasData) {
                    String currDate = blockingQueue.take();

                    processCurrentDate(currDate);

                    isCurrThreadInterrupted = Thread.currentThread().isInterrupted();
                    hasMoreItemsToRead = readLatch.getCount() > 0;
                    queueHasData = !blockingQueue.isEmpty();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                writeLatch.countDown();
            }
        }

        private void processCurrentDate(String currDate) throws InterruptedException {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " :processed date as " + currDate);
        }
    }
    public void start(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
        CountDownLatch readLatch = new CountDownLatch(1);
        Producer producer = new Producer(blockingQueue, readLatch);
        new Thread(producer).start();

        int threadPoolSize = MAX_THREAD_POOL_SIZE;

        CountDownLatch writeLatch = new CountDownLatch(threadPoolSize);
        Consumer consumer = new Consumer(blockingQueue, readLatch, writeLatch);

        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);

        for(int i=0;i<threadPoolSize;i++){
            executorService.execute(consumer);
        }

        readLatch.await();
        writeLatch.await();
        System.out.println("All the countDownLatches are over now... shutting down.");
        executorService.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer1 producerConsumer = new ProducerConsumer1();
        
        producerConsumer.start(args);
    }
}
