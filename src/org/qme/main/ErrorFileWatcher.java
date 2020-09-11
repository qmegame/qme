package org.qme.main;

import java.io.File;

import javax.swing.JOptionPane;

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
			JOptionPane.showMessageDialog(null, "An error has occurred. Please find your " + Main.ERROR_LOG + "file and send the error message to a dev.");
			System.exit(0);
		}
	}
}
