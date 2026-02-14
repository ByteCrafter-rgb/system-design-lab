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

## Day 4

It might seem primitive, but Head-of-Line (HOL) Blocking was a defining limitation of the internet for decades. In the early days of the web, efficiency was sacrificed for simplicity, and we are still evolving protocols today to fully escape it.

You might think, "Why not just move the fast tasks to the front?" In a real network (like TCP), this is incredibly hard because:

Dependency: Sometimes Task B needs the result of Task A.

State: The server might not know how long a task will take until it actually starts running it.

Integrity: If packets arrive out of order, the application might get corrupted data.

![Network Tab Waterfall](/assets/network-tab.png)

## Day 5

Due to arrival rate of requests being higher than the rate of servicing of requests, queue formation is inherent part of any system.
