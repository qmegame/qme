package org.qme.utils;

/**
 * The class that handles multithreading
 * @author Tom
 * @since preC
 */

public class Multithreading implements Runnable {
    private Thread t;
    private String threadName;

    public Multithreading(String name) {
        threadName = name;
        System.out.println("Creating " +  threadName );
    }

    public void run() {
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}
