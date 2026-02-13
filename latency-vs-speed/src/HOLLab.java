import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

public class HOLLab {
    public static void main(String[] args) throws Exception {
        // A single thread = a single-lane pipe (HTTP/1.1 style)
        ExecutorService connection = Executors.newSingleThreadExecutor();
        List<Long> latencies = new ArrayList<>();

        System.out.println("--- Scenario: Head-of-Line Blocking ---");

        // 1. The "Monster" (The big hero image)
        long arrivalMonster = System.currentTimeMillis();
        connection.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            } // 2s delay
            return null;
        });

        // 2. The "Fast Tasks" (99 small CSS/JS files)
        for (int i = 0; i < 99; i++) {
            long arrivalFast = System.currentTimeMillis();
            connection.submit(() -> {
                long startFast = System.currentTimeMillis();
                latencies.add(startFast - arrivalFast);
                return null;
            });
        }

        connection.shutdown();
        connection.awaitTermination(10, TimeUnit.SECONDS);

        // Analyze the damage
        latencies.sort(Long::compare);
        System.out.println("P50 (Median) Latency: " + latencies.get(latencies.size() / 2) + "ms");
        System.out.println("P99 (Tail) Latency: " + latencies.get(latencies.size() - 1) + "ms");
    }
}