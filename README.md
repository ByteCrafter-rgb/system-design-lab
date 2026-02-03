# system-design-lab

Repo for learning system design from the bare bones

## Day 1

Learning about latency and wait time.

```
Latency is largely the time spent waiting and not the time spent to do the processing.
```

## Day 2

Learning about multi-threading and parallelism and difference between both.

```
Single core CPUs achieve concurrency through context switching mid task to mimic parallelism.
It still experiences wait time.
Parallesim : simultaneous exceution
Concurrency : interleaving progress
```

## Day 3

Concurrency allows the CPU to switch between tasks, but in Java's ExecutorService, a Thread is the unit of execution.

```
One thread can only execute one task at a time in java.
```
