package org.ftpviewer.demo;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import org.ftpviewer.controller.Controller;
import org.ftpviewer.model.BasicFile;
import org.ftpviewer.timer.TimerUtils;

public class FTPConnectAndLoginDemo {
	private static void showServerReply(FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		if (replies != null && replies.length > 0) {
			for (String aReplay : replies) {
				System.out.println("SERVER: " + aReplay);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		connect();
	}

	public static FTPClient connect() throws Exception {
		String server = "192.168.1.100";
		int port = 21;
		String user = "FTP-User";
		String pass = "FTP123qweasd!";
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(server, port);
			showServerReply(ftpClient);
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				System.out.println("Operation failed. Server reply code: " + replyCode);
				return null;
			}
			boolean success = ftpClient.login(user, pass);
			showServerReply(ftpClient);
			if (!success) {
				System.out.println("Could not login to the server");
				return null;
			} else {
				System.out.println("LOGGED IN SERVER");
			}
		} catch (IOException ex) {
			System.out.println("Oops! Something wrong happened");
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ftpClient.configure(new FTPClientConfig(FTPClientConfig.SYST_NT));

		doStuff(ftpClient);

//		doStuff2(ftpClient);

		return ftpClient;
	}

//	public static BasicFile[] getRootBasicFiles(FTPClient ftpClient) {
//		
//	}

//	private File[] buildFileSystemFromFtp(FTPClient ftpClient) throws Exception {
//		FTPFile[] files = ftpClient.listFiles();
//		
//		return null;
//	}`
//	
//	private File transformFtpFileIntoFile(FTPFile ftpFile, String parentPath) {
//		File file = new File(parentPath + "/" + ftpFile.getName());
//	}

	public static FTPFile[] getRootFiles(FTPClient ftpClient) throws IOException {
		FTPFile[] files = ftpClient.listFiles();
		for (FTPFile file : files) {
//				file.get
			System.out.println(file);
		}
		return null;
	}

	private static void doStuff2(FTPClient ftpClient) {

		try {
			//			ftpClient.configure(new FTPClientConfig(FTPClientConfig.SYST_UNIX));

			//			boolean directoryExists = ftpClient.changeWorkingDirectory("/");

			FTPFile[] files3 = ftpClient.listFiles();
			for (FTPFile file : files3) {
				System.out.println(file);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void doStuff(FTPClient ftpClient) throws Exception {

		//		FTPListParseEngine engine = ftpClient.initiateListParsing("/Game");
		//
		//		while (engine.hasNext()) {
		//			FTPFile[] files = engine.getNext(25); // "page size" you want
		//			//do whatever you want with these files, display them, etc.
		//			//expensive FTPFile objects not created until needed.
		//			for (FTPFile file : files) {
		//				System.out.println(file);
		//			}
		//		}

		//		FTPFile[] files = ftpClient.listFiles();
		//		for (FTPFile file : files) {
		//			System.out.println(file);
		//		}
		//
		//		FTPFile[] folders = ftpClient.listDirectories("/Game/Data");
		//		for (FTPFile folder : folders) {
		//			System.out.println(folder);
		//		}
		//
		//		FTPListParseEngine engine = ftpClient.initiateListParsing("/Game/");
		//
		//		while (engine.hasNext()) {
		//			FTPFile[] files2 = engine.getNext(25); // "page size" you want
		//			//do whatever you want with these files, display them, etc.
		//			//expensive FTPFile objects not created until needed.
		//			for (FTPFile file : files2) {
		//				System.out.println(file);
		//			}
		//		}

		//		boolean directoryExists = ftpClient.changeWorkingDirectory("/Game");
		//
		//		FTPFile[] files3 = ftpClient.listFiles();
		//		for (FTPFile file : files3) {
		//			System.out.println(file);
		//		}
		//
		//		System.out.println(directoryExists);





//		BasicFile root = new BasicFile(null, null, 0, true, null);
//
//		TimerUtils.startTime();
////		Controller.scan(ftpClient, "/Game/", root);
//		Controller.scan(ftpClient, "/", root);
//		TimerUtils.endTime("Scan:");
//
//		Controller.printFiles(root);
//
//		System.out.println(root);
//
//		TimerUtils.startTime();
////		Controller.writeBasicFileObjectToFile(root, "");
////		TimerUtils.endTime("Writing object to file:");





		BasicFile root2 = null;
		TimerUtils.startTime();
		root2 = Controller.readObjectFromFile("");
		TimerUtils.endTime("Reading object from file:");
//
		TimerUtils.startTime();
		Controller.computeFolderSize(root2);
		TimerUtils.endTime("Computing folder size:");
//
		Controller.printFiles(root2);

	}

}
