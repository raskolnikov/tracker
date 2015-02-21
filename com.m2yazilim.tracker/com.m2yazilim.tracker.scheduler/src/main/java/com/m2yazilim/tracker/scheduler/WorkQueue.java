package com.m2yazilim.tracker.scheduler;

import java.util.LinkedList;

public class WorkQueue {
    LinkedList queue = new LinkedList();

    // Add work to the work queue
    public synchronized void addWork(Object o) {
        queue.addLast(o);
        notify();
    }

    // Retrieve work from the work queue; block if the queue is empty
    public synchronized Object getWork() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return queue.removeFirst();
    }
}