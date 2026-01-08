import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String args[]) {
        List<Task> queue = Arrays.asList(
                new Task("Monster Task 1", 3000),
                new Task("Fast Task 1", 10),
                new Task("Fast Task 2", 10));

        System.out.println("Starting single thread rpocessing...\n");

        for (Task task : queue) {
            task.startTime = System.currentTimeMillis();

            try {
                Thread.sleep(task.processingTimeMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            task.endTime = System.currentTimeMillis();

            long waitTime = task.startTime - task.arrivalTime;
            long totalLatency = task.endTime - task.arrivalTime;

            System.out.printf("Task: %s | Wait: %dms | Work: %dms | Total Latency: %dms%n",
                    task.name, waitTime, task.processingTimeMs, totalLatency);
        }

    }
}
