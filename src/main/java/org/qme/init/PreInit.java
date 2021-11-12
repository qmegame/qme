package org.qme.init;

import org.qme.io.Logger;
import org.qme.io.Severity;

import java.io.File;

/**
 * This class contains the utilities for making sure the environment is set up
 * correctly, and initializes logging and other necessary I/O. Before
 * PreInit.init is called, logging capabilities do not exist.
 * @author adamhutchings
 * @since 0.4
 */
public class PreInit {

    private static String QDATA = "qdata/";

    /**
     * No initialization!
     */
    private PreInit() { throw new IllegalStateException("PreInit"); }

    /**
     * Initialize log files.
     */
    public static void init() {

        try {
            new File(QDATA).mkdir();
        } catch(SecurityException e) {
            System.exit(-1); // TODO
        }

        Logger.logsActivated = true;

        Logger.log("Successfully initialized logger, pre-init done.", Severity.NORMAL);

    }

}
