# Java Multithreaded sudoku puzzle solutions validator 

## Introduction

I've decided to make this small project to dive in Java threads. Here I used
basic technologies and strategies of multithreading programming described below:
- Threads
- "Runnable" interface 
- Thread pool
- Synchronized methods 
- Atomic access. 

With this project you can see how threads can be implemented, how data can be
shared between them, how to use synchronized methods and atomic access to 
protect from thread interference and memory consistency errors. I hope that
my project will help someone to learn how threads are working.

A Sudoku puzzle uses a 9 × 9 grid in which each column and row, as well as
each of the nine 3 × 3 subgrids, must contain all of the digits 1 ··· 9. This
project consists of designing solution validation algorithm and implementing 
the way to validate solution in different threads. I choose to use the next
strategy for multithreading my application:
- Nine threads to check that each row contains the digits 1 through 9
- Nine threads to check that each column contains the digits 1 through 9
- Nine threads to check that each of the 3 × 3 subgrids contains the digits 1
through 9

So let's look on the application itself.

## Threads

There are two ways to implement threads in Java:

### Extend "Thread" class

You can create a class that extends the **Thread** class and override **run()**
method:
```java:
    public class MyClass extends Thread {
        @Override
        public void run() {
            // This code will run in a separate thread
        }
    }
```

### Implement "Runnable" interface

You can also create a class that implements **Runnable** interface and override
**run()** method as well:
```java:
    public class MyClass implements Runnable {
        @Override
        public void run() {
            // This code will run in a separate thread
        }
    }
```

To run your code in a new thread you can simply do next:
```java:
    // running code from class that extends Thread class
    
    MyClass threadClass = new MyClass();
    threadClass.start();
    
    // running code from class that implements Runnable intrface
    
    Thread myThread = new Thread(new MyClass());
    myThread.start();
```

In my case I choose to implement **Runnable** interface because one class can 
extend only one other class and implement as much interfaces as needed. In my 
project I implemented **Factory** pattern in **ThreadFactory** class. This 
class will responsible for creating all threads that my program will need.
```java:

    public final class ThreadFactory {
        private static ThreadFactory factoryInstance;

        public static ThreadFactory getInstance() {
            if (factoryInstance == null) {
                factoryInstance = new ThreadFactory();
            }
            return factoryInstance;
        }

        public Thread createThread(Runnable obj) {
            return new Thread(obj);
        }
    }
```
I've also implemented **Singleton** pattern here to make sure that there is 
only one ThreadFactory instance created in my program.

## Thread Pool

To provide unified access to all of created threads I created a class called
**ThreadPool**. Thread pool helps you manage your threads by providing
access to each one of them. It also allows you to restrict the number of threads
that you can create in your program. I tried to implement one by myself, 
but you can simply make your class implement **Executor** interface and 
invoke *newFixedThreadPool* method. You can check more about built-in 
[Executor interfaces](https://docs.oracle.com/javase/tutorial/essential/concurrency/exinter.html)
here. Below is my silly **Thread Pool** implementation.
```java:
    public final class ThreadPool {
        private static ThreadPool threadPoolInstance;
        private List<Thread> threads = new ArrayList<>();

        public static ThreadPool getThreadPoolInstance() {
            if (threadPoolInstance == null) {
                threadPoolInstance = new ThreadPool();
            }
            return threadPoolInstance;
        }

        public int getThreadsPoolSize() {
            return this.threads.size();
        }
        public void addThreadToPool(Thread t) {
            this.threads.add(t);
        }

        public Object executeThread(int id) {
            threads.get(id).start();
            return threads.get(id);
        }

        public void executeAllThreads() {
            threads.forEach(Thread::start);
        }

        public void joinThread(int id) throws InterruptedException {
            threads.get(id).join();
        }

        public void deleteThread(int id) {
            threads.remove(id);
        }
    }
```
Here I also implemented a **Singleton** design pattern to make sure that my
program uses only one Thread Pool.

## Sharing data

Threads are sharing application's data between themselves. In my project all
threads after they've finished their job writes 1 or 0's in a list so I could
check if any row, column or subgrid are valid. To do so I've just passed the
reference to my list to all threads in a constructor.
```java:
    public class Solution {
        public static volatile List<Integer> results = new ArrayList<>(27);
        
        // other code goes bellow
    }
```
Here I mark my list with *volatile* keyword. As far as I understand it is used
for make this variable visible to all threads.
```java:
    public class RowValidator implements Runnable {
        private int[] row;
        private List<Integer> result;
        public RowValidator(int[] row, List<Integer> results) {
            this.row = row;
            this.result = results;
        }
        @Override
        public void run() {
            // code that runs in separate thread now can access shared result variable
        }
    }
```
But here comes another problems. As *results* list became shared between all 
threads, some of them may modify it at once. That may lead to **Thread 
interference** or **Memory Consistency Errors**. To protect form that we can
give access to the shred data via **Synchronized methods**.
```java:
    public synchronized void checkRow() {
        // validate the row and modify results value inside this method
    }
```
Since I access shared data from synchronized data, it has several effects:

- It is not possible for two invocations of synchronized methods on the same
object to interleave.
- When one thread is executing a synchronized method for an object, all other
threads that invoke synchronized methods for the same object block (suspend
execution) until the first thread is done with the object.
- This guarantees that change to the state of the objects are visible to all
threads.

I also use *joins* to make the main thread wait until all child threads finish
their work and modify *results* list.
```java:
    public int validate(String[] args) throws NoSuchFieldException, InterruptedException {

        AtomicInteger answer = new AtomicInteger(1);
        
        // creates all threads and adds them to a ThreadPool instance
        
        pool.executeAllThreads(); // starting all threads
        
        // making main thread wait for all child threads finishing their job

        for (int i = 0; i < 27; i++) {
            pool.joinThread(i);
        }

        // checking results list for answers from all threads;

        results.forEach(integer -> {
            //System.out.println(integer);
            if (integer == 0) answer.set(0);
        });
        System.out.println(results.toString());
        return answer.get();
    }
```
Here I also use *AtomicInteger* variable to make sure that it will be able 
to be modified at any time as **Atomic access** ensures that operations that
happens on this variable can't be interrupted and will be completed.

## Conclusion

This project meant to show base concepts of multithreading programming in Java.
Many things are not covered, such as **deadlocks**, **immutable objects**, 
**guarded objects** and so on. You can check [this](https://www.oreilly.com/library/view/java-concurrency-in/0321349601/)
book for more examples and advanced explanations. Hope my project will help 
someone to understand basic concepts of threads.