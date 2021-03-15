package org.qme.io;

import org.qme.utils.Performance;
import org.qme.utils.SplashText;

import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;

/**
 * A class to log things wanting to be logged to "qdata/logs.txt"
 * @author santiago, jakeroggenbuck
 * @since 0.1.0
 */
public class Logger {

	/**
	 * Whether the logger has been initialized yet.
	 */
	public static boolean logsActivated = false;
	
	/**
	 * Function that logs a message to "qdata/logs.txt" with the date and time,
	 * the severity, and a message
	 * @author santiago, jakeroggenbuck
	 * @since 0.1.0
	 * @param message The custom message to be logged
	 * @param severity An estimation of the severity of the error
	 */
	public static synchronized void log(String message, Severity severity) {

		if (!logsActivated) return;
		
		// Creates file if not created
		final File logs = new File("qdata/logs.txt");
		try {
			logs.createNewFile();
		} catch(IOException | SecurityException e) {
			showDialog("Error writing errors to log. Fatal. Possible cause: insufficient permissions.");
			System.exit(-1);
		}

		String error;

		// Error format
		if (severity == Severity.RAW) {
			error = message + "\n";
		} else {
			error = "[ " + LocalDateTime.now().toString() + " ] [ " + severity.name() + " ] " + message + "\n";
		}

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
		//JOptionPane.showMessageDialog(null, message, null, JOptionPane.ERROR_MESSAGE);
	}


	public static void printCrashReport(Crash crash) {

		log("\n\n ██████╗ ███╗   ███╗███████╗    ██╗  ██╗ █████╗ ███████╗     ██████╗██████╗  █████╗ ███████╗██╗  ██╗███████╗██████╗ \n" +
				"██╔═══██╗████╗ ████║██╔════╝    ██║  ██║██╔══██╗██╔════╝    ██╔════╝██╔══██╗██╔══██╗██╔════╝██║  ██║██╔════╝██╔══██╗\n" +
				"██║   ██║██╔████╔██║█████╗      ███████║███████║███████╗    ██║     ██████╔╝███████║███████╗███████║█████╗  ██║  ██║\n" +
				"██║▄▄ ██║██║╚██╔╝██║██╔══╝      ██╔══██║██╔══██║╚════██║    ██║     ██╔══██╗██╔══██║╚════██║██╔══██║██╔══╝  ██║  ██║\n" +
				"╚██████╔╝██║ ╚═╝ ██║███████╗    ██║  ██║██║  ██║███████║    ╚██████╗██║  ██║██║  ██║███████║██║  ██║███████╗██████╔╝\n" +
				" ╚══▀▀═╝ ╚═╝     ╚═╝╚══════╝    ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝     ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝╚══════╝╚═════╝ \n", Severity.RAW);

		try {
			log(SplashText.grabSplash(new File(Logger.class.getResource("/misc/crash_messages.txt").getPath())), Severity.RAW);
		} catch (FileNotFoundException e) {
			log("Bro I couldn't find the crash_messages.txt file wtf", Severity.RAW);
		}

		log("The information attached to this error will now be printed.\n", Severity.RAW);

		log("Running game version v" + Performance.GAME_VERSION + "" +
				"\nJVM: " + Performance.JAVA_VERSION + " (Vendor: " + Performance.JAVA_VENDOR + ")" +
				"\nOperating System: " + Performance.OPERATING_SYSTEM + " (Arch: " + Performance.ARCH_TYPE + ") (Version: " + Performance.OPERATING_SYSTEM_VERSION + ")" +
				"\nGraphics: " + Performance.GPU_NAME + " " + Performance.GPU_VENDOR +
				"\nMemory: (Max: " + Runtime.getRuntime().totalMemory() / 1000000 + "mb) (Used: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000000 + "mb)" +
				"\nProcessor: " + Performance.CPU + "\n", Severity.RAW);

		log("Message: " + crash.exception.getMessage(), Severity.RAW);
		log(getStackTrace(crash.exception), Severity.RAW);

		if (crash.isFatal) {
			log("The process will now terminate", Severity.RAW);
			System.exit(1);
		}

	}

	private static String getStackTrace(final Throwable throwable) {
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw, true);
		throwable.printStackTrace(pw);
		return sw.getBuffer().toString();
	}

}
