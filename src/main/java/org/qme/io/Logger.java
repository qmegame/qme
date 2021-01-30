package org.qme.io;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * A class to log things wanting to be logged to "qdata/logs.txt"
 * @author santiago, jakeroggenbuck
 * @since preA
 */
public class Logger {
	
	/**
	 * Function that logs a message to "qdata/logs.txt" with the date and time,
	 * the severity, and a message
	 * @author santiago, jakeroggenbuck
	 * @since preA
	 * @param message The custom message to be logged
	 * @param severity An estimation of the severity of the error
	 * @return void
	 * @throws IOException
	 */
	public static synchronized void log(String message, Severity severity) {

		// Creates directory if not already created
		try {
			new File("qdata/").mkdir();
		} catch(SecurityException e) {
			showDialog("Error creating error log folder. Fatal. Possible cause: insufficient permissions.");
		}
		
		// Creates file if not created
		final File logs = new File("qdata/logs.txt");
		try {
			logs.createNewFile();
		} catch(IOException | SecurityException e) {
			showDialog("Error writing errors to log. Fatal. Possible cause: insufficient permissions.");
			System.exit(-1);
		}
		
		// Error format
		String error = "[ " + LocalDateTime.now().toString() + " ] [ " + severity.name() + " ] " + message + "\n";

		if (severity != Severity.DEBUG) {
			// Print to output if something is not normal
			System.out.print(error);
		}
		// Write to file
		FileWriter write = null;
		try {
			write = new FileWriter(logs, true);
		} catch(IOException | SecurityException e) {
			showDialog("Error writing errors to log. Fatal. Possible cause: insufficient permissions.");
			System.exit(-1);
		}
		try {
			write.write(error);
			write.close();
		} catch(IOException e) {
			showDialog("Error writing errors. Nonfatal.");
		}

		if (severity == Severity.FATAL) {
			showDialog("FATAL " + message);
		}

	}

	/**
	 * Creates dialog box, wrapper for JOptionPane.showMessageDialog();
	 * @param message information about error
	 */
	private static void showDialog(String message) {
		JOptionPane.showMessageDialog(null, message, null, JOptionPane.ERROR_MESSAGE);
	}

}
