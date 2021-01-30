package org.qme.utils;

import org.qme.io.Logger;
import org.qme.io.Severity;

/**
 * The class that handles multithreading
 * @author Tom
 * @since preC
 */

public class Multithreading implements Runnable {
    private Thread t;
    private final String threadName;

    public Multithreading(String name) {
        threadName = name;
        Logger.log("Creating " +  threadName, Severity.DEBUG);
    }

    public synchronized void start () {
        Logger.log("Starting " +  threadName, Severity.NORMAL);
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }

    @Override
    public void run () {
        Logger.log("Running " +  threadName, Severity.NORMAL);
        try {
            for(int i = 4; i > 0; i--) {
                Logger.log("Thread: " + threadName + ", " + i, Severity.DEBUG);

                // Let the thread sleep for a while.
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            Logger.log("Thread " +  threadName + " interrupted.", Severity.FATAL);
            Thread.currentThread().interrupt();
        }
        Logger.log("Thread " +  threadName + " exiting.", Severity.DEBUG);
    }

    public void stop() {
        t.interrupt();
    }
}
