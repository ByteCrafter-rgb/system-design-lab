
import java.util.concurrent.*;

public class BulkheadLab {
    public static void main(String[] args) throws InterruptedException {
        // 1. Separate resources: Two pools that don't share threads
        ExecutorService poolA = Executors.newFixedThreadPool(2);
        ExecutorService poolB = Executors.newFixedThreadPool(1);

        System.out.println("--- Scenario: Isolated Resources (Bulkheading) ---");

        // 2. Service A: Flood its own pool with 100 Monster Tasks
        for (int i = 1; i <= 100; i++) {
            poolA.submit(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
            });
        }
        System.out.println("Service A pool is now completely saturated...");

        // 3. Service B: Submits a "Fast Task" to its PRIVATE pool
        long arrivalB = System.currentTimeMillis();
        poolB.submit(() -> {
            long startB = System.currentTimeMillis();
            System.out.println("Service B: Started! Wait time: " + (startB - arrivalB) + "ms");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        });

        poolA.shutdownNow(); // Clean up the 100 tasks
        poolB.shutdown();
    }
}