package org.qme.logging;

import java.io.File;

import static org.qme.logging.QLoggingUtils.FS;
import static org.qme.logging.QLoggingUtils.QDATA;
import static org.qme.main.Main.displayError;

/**
 * @author S-Mackenzie1678
 * @since pre2
 */
public class FileWatcher {
	
	public static final String ERROR_LOG = QDATA + FS + "error_logs.txt";
	
	private long lastTime;
	private File watched;
	
	public FileWatcher(File file) {
		this.watched = file;
		this.lastTime = file.lastModified();
	}
	
	public void check() {
		long actualLastTime = this.watched.lastModified();
		if(actualLastTime != this.lastTime) {
			displayError("An error has occurred. Please find your " + System.getProperty("user.dir") + FS + ERROR_LOG + " file and send the error message to a dev.", false);
			System.exit(0);
		}
	}
}
