package org.ftpviewer.timer;

import java.util.concurrent.TimeUnit;

/**
 * Utility class used to measure the time it takes for code to execute.
 */
public class TimerUtils {

	private static long startTime;

	public static void startTime() {
		startTime = System.nanoTime();
	}

	public static void endTime() {
		endTime("");
	}
	
	public static void endTime(String message) {
		long elapsedTime = System.nanoTime() - startTime;
		long time = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
		if (time != 0) {
			printTime(message, time, TimeUnit.SECONDS);
		} else {
			time = TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
			printTime(message, time, TimeUnit.MILLISECONDS);
		}
	}
	
	private static void printTime(String message, long elapsedTime, TimeUnit unit) {
		System.err.println(message + " " + elapsedTime + " " + unit.toString().toLowerCase());
	}

	public static void endTimeBasic(String message) {
		endTimeBasic(message, TimeUnit.SECONDS);
	}

	public static void endTimeBasic(String message, TimeUnit unit) {
		long elapsedTime = System.nanoTime() - startTime;
		System.out.println(message + " " + unit.convert(elapsedTime, TimeUnit.NANOSECONDS) + " " + unit.toString().toLowerCase());
	}

}
