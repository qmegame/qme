package org.qme.io;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * A class to log things wanting to be logged to "qdata/logs.txt"
 * @author santiago
 * @since preA
 */
public class Logger {
	
	/**
	 * Function that logs a message to "qdata/logs.txt" with the date and time,
	 * the severity, and a message
	 * @author santiago
	 * @since preA
	 * @param message The custom message to be logged
	 * @param severity An estimation of the severity of the error
	 * @return void
	 * @throws IOException
	 */
	public static void log(String message, Severity severity) {
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
		} catch(IOException e) {
			showDialog("Error writing errors to log. Fatal. Possible cause: insufficient permissions.");
			System.exit(-1);
		} catch(SecurityException f) {
			showDialog("Error writing errors to log. Fatal. Possible cause: insufficient permissions.");
			System.exit(-1);
		}
		
		// String to be written
		String error = "";
		
		// Append newline from pervious error
		error += "\n";
		
		// Append date and time to message
		error += LocalDateTime.now().toString();
		
		// Append formatting
		error += " - ";
		
		// Append severity
		error += severity.name();
		
		// Append formatting
		error += ": ";
		
		// Append message
		error += "\"";
		error += message;
		error += "\"";

		// Print to output
		//System.out.print(error);
		
		// Write to file
		FileWriter write = null;
		try {
			write = new FileWriter(logs, true);
		} catch(IOException e) {
			showDialog("Error writing errors to log. Fatal. Possible cause: insufficient permissions.");
			System.exit(-1);
		} catch(SecurityException f) {
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
			showDialog(message + ". Fatal.");
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