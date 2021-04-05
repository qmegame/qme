package org.qme.io;

public class Crash {

    public Exception exception;
    public String description;
    public boolean isFatal;

    /**
     * Generates a crash report given an exception
     * @param exception The exception that caused this crash report
     * @param description A description of the cause of the crash
     * @param isFatal should the logging of this crash report terminate the program
     */
    public Crash(Exception exception, String description, boolean isFatal) {
        this.exception = exception;
        this.description = description;
        this.isFatal = isFatal;
    }

}
