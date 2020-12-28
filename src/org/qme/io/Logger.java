package org.qme.io;

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
		// Creates file if not created
		final File logs = new File("qdata/logs.txt");
		try {
			logs.createNewFile();
		} catch(IOException e) {
			Logger.log("Error generating or accessing log file.", Severity.ERROR);
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
		error += severity.toString();
		
		// Append formatting
		error += ": ";
		
		// Append message
		error += "\"";
		error += message;
		error += "\"";
		
		// Write to file
		try {
			final FileWriter write = new FileWriter(logs);
			write.write(error);
			write.close();
		} catch(IOException e) {
			Logger.log("Error writing to error log.", Severity.WARNING);
		}
	}
}