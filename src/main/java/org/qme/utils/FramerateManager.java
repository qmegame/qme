package org.qme.utils;

import org.qme.client.Application;
import org.qme.io.Logger;
import org.qme.io.Severity;

import java.util.concurrent.TimeUnit;

/**
 * This class contains the necessary code to lock the frame rate to 60 and limit
 * GPU use.
 * @author adamhutchings
 * @since 0.4
 */
public class FramerateManager {

    private static final int FRAMERATE_MAX = 60;

    /**
     * Whether the screen should be refreshed
     */
    public static volatile boolean refresh = false;

    /**
     * Runs forever, and waits until the screen should be refreshed.
     */
    public static final Thread refreshUpdater = new Thread() {
        @Override
        public void run() {
            while (Application.running) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000 / FRAMERATE_MAX);
                    refresh = true;
                } catch (InterruptedException e) {
                    Logger.log("Refresh update thread interrupted, shutting down ...",
                            Severity.WARNING);
                }
            }
            Logger.log("Application stopped, terminating refresh thread ...",
                    Severity.DEBUG);
        }
    };

}
