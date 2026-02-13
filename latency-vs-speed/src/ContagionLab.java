package src;

import java.util.concurrent.*;

public class ContagionLab {
    public static void main(String[] args) throws InterruptedException {
        // 1. Create a pool with only 2 threads (our limited resource)
        ExecutorService sharedResource = Executors.newFixedThreadPool(2);

        System.out.println("--- Scenario: Service A hogs the resource ---");

        // 2. Service A: Submits 2 "Monster Tasks" that take 5 seconds each
        for (int i = 1; i <= 2; i++) {
            int taskId = i;
            sharedResource.submit(() -> {
                System.out.println("Service A (Task " + taskId + "): Starting 5s work...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                System.out.println("Service A (Task " + taskId + "): Finished.");
            });
        }

        // 3. Small delay to ensure Service A tasks have started
        Thread.sleep(100);

        // 4. Service B: A "Fast Task" arrives 100ms later
        long arrivalB = System.currentTimeMillis();
        sharedResource.submit(() -> {
            long startB = System.currentTimeMillis();
            System.out.println("Service B: Finally started! Wait time: " + (startB - arrivalB) + "ms");
            // Fast work
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        });

        sharedResource.shutdown();
        sharedResource.awaitTermination(10, TimeUnit.SECONDS);
    }
}