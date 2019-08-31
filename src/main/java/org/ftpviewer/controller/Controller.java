package org.ftpviewer.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import org.ftpviewer.model.BasicFile;

public class Controller {

	public static void scan(FTPClient ftpClient, String path, BasicFile parent) throws FileNotFoundException, IOException, IllegalArgumentException {

		checkArgumentsForNull(ftpClient, path, parent);

		if (!ftpClient.changeWorkingDirectory(path)) {
			//			throw new FileNotFoundException("The given path (" + path + ") is not a folder.");
			return;
		}

		FTPFile[] files = ftpClient.listFiles();
		for (FTPFile file : files) {
			String newPath = null;
			if (path.endsWith("/")) {
				newPath = path + file.getName();
			} else {
				newPath = path + "/" + file.getName();
			}

			BasicFile basicFile = new BasicFile(file.getName(), newPath, file.getSize(), file.isDirectory(), parent);
			parent.addChildFile(basicFile);
			if (file.isDirectory()) {
				scan(ftpClient, newPath, basicFile);
			}
		}
	}

	//	public static BasicFile scanWithRoot(FTPClient ftpClient, String path) throws FileNotFoundException, IOException, IllegalArgumentException {
	//		checkArgumentsForNull(ftpClient, path);
	//		if (!ftpClient.changeWorkingDirectory(path)) {
	//			throw new FileNotFoundException("The given path (" + path + ") is not a folder.");
	//		}
	//		
	//		return null;
	//
	//	}

	//	private static int indent = 0;

	public static void printFiles(BasicFile root) {
		//		indent += 3;
		for (BasicFile file : root.getChildFiles()) {
			System.out.println(file);
			printFiles(file);
		}
	}

    /**
     * Write root {@link BasicFile} to filesystem so that it can be loaded from
     * disk by using {@link Controller#readObjectFromFile(String)}.
     */
	public static void writeBasicFileObjectToFile(BasicFile basicFile, String path) {
		try {
			FileOutputStream fos = new FileOutputStream("C:\\Users\\gh0st\\Desktop\\ftp_folders.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(basicFile);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * Create a {@link BasicFile} by loading the file at the specified path.
     */
	public static BasicFile readObjectFromFile(String path) {
		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\gh0st\\Desktop\\ftp_folders.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			BasicFile basicFile = (BasicFile) ois.readObject();
			ois.close();
			return basicFile;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static long computeFolderSize(BasicFile basicFile) {
		if (basicFile.isFolder()) {
			for (BasicFile file : basicFile.getChildFiles()) {
				if (!file.isFolder()) {
					basicFile.setSize(basicFile.getSize() + file.getSize());
				} else {
					basicFile.setSize(basicFile.getSize() + computeFolderSize(file));
				}
			}
		}
		return basicFile.getSize();
	}

	//	private static String generateIndent() {
	//		String result = "";
	//		for (int i = 0; i < indent; i++) {
	//			result += " ";
	//		}
	//		return result;
	//	}

	private static void checkArgumentsForNull(Object... arguments) throws IllegalArgumentException {
		for (Object argument : arguments) {
			if (argument == null) {
				throw new IllegalArgumentException("Argument " + argument + " cannot be null.");
			}
		}
	}
	
	public static String humanReadableByteCount(long bytes, boolean si) {
		int unit = si ? 1000 : 1024;
		if (bytes < unit)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = (si ? "KMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "");
		if (pre.equals("K")) {
			return String.format("%.0f %sB", bytes / Math.pow(unit, exp), pre);
		}
		return String.format("%.2f %sB", bytes / Math.pow(unit, exp), pre);
	}

	//	public static String humanReadableByteCount2(long bytes, boolean si) {
	//	    int unit = si ? 1000 : 1024;
	//	    if (bytes < unit) return bytes + " B";
	//	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	//	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
	//	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	//	}

}
