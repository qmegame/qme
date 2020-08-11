package org.qme.main;

import java.util.concurrent.TimeUnit;

public class Main {
	
	public static final int FRAMERATE = 30;
	
	public static void main(String[] args) throws InterruptedException {
		
		QApplication app = new QApplication();
		
		// Main loop time
		while (true) {
			
			// Wait for some time
			TimeUnit.MILLISECONDS.sleep(1000 / FRAMERATE);
			
			app.reload();
		}
		
	}

}
