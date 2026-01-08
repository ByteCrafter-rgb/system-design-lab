public class Task {

    String name;
    long processingTimeMs;
    long arrivalTime;
    long endTime;
    long startTime;

    public Task(String name, long processingTimeMs) {
        this.name = name;
        this.processingTimeMs = processingTimeMs;
        this.arrivalTime = System.currentTimeMillis();
    }
}