package org.qme.main;

import java.io.File;

/**
 * @author S-Mackenzie1678
 * @since pre2
 */
public class ErrorFileWatcher {
	
	private long lastTime;
	private File watched;
	
	public ErrorFileWatcher(File file) {
		this.watched = file;
		this.lastTime = file.lastModified();
	}
	
	public void check() {
		long actualLastTime = this.watched.lastModified();
		if(actualLastTime != this.lastTime) {
			System.exit(0);
		}
	}
}
