import java.util.concurrent.LinkedBlockingQueue;

public class QueueLab {
    public static void main(String[] args) throws InterruptedException {
        // A thread-safe queue so both threads can access it safely
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

        // 1. ARRIVAL THREAD (The Producer)
        Thread arrivalThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    Thread.sleep(1200); // New task every 0.5s
                    String task = "Task " + i;
                    queue.add(task);
                    System.out.println("Task " + i + " arrived. Queue size is now: " + queue.size());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        arrivalThread.start();

        // 2. PROCESSING LOOP (The Consumer)
        System.out.println("--- Starting Real-Time Queue Lab ---");
        int tasksFinished = 0;
        while (tasksFinished < 5) {
            // "take" blocks until something is available in the queue
            String currentTask = queue.take();
            System.out.println("   Processing " + currentTask + "...");

            Thread.sleep(500); // Service takes 1.2s (Slower than arrivals)

            System.out.println("   " + currentTask + " finished!");
            tasksFinished++;
        }
    }
}