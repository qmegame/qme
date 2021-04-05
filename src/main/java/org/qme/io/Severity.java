package org.qme.io;

/**
 * An enum that represents the severity of errors or other events needing to be
 * logged
 * @author santiago
 * @since 0.1.0
 */
public enum Severity {
	RAW, // Will get logged but without formatting
	NORMAL,
	DEBUG,
	WARNING,
	ERROR,
	FATAL
}
