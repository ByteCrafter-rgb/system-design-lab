import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;

public class ConcurrencyLab {
    public static void main(String[] args) throws InterruptedException {
        // A pool of ONE thread: Concurrency allowed (queueing), but NO parallelism.
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Task> tasks = new ArrayList<>();

        // Create tasks (same as before)
        tasks.add(new Task("Monster Task", 3000));
        tasks.add(new Task("Fast Task 1", 10));
        tasks.add(new Task("Fast Task 2", 10));

        System.out.println("Submitting tasks to a Single-Threaded Executor...\n");

        for (Task t : tasks) {
            executor.submit(() -> {
                t.startTime = System.currentTimeMillis();
                try {
                    Thread.sleep(t.processingTimeMs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t.endTime = System.currentTimeMillis();

                long waitTime = t.startTime - t.arrivalTime;
                System.out.printf("Task: %s | Wait: %dms | Latency: %dms%n",
                        t.name, waitTime, (t.endTime - t.arrivalTime));
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}