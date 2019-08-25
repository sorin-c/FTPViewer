package org.ftpviewer.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BasicFile implements Serializable {

	//	private static final long serialVersionUID = -8234890005046219853L;
	private static final long serialVersionUID = 6432464470693932263L;
	private String path;
	private String name;
	private long size;
	private boolean isFolder;

	private BasicFile parentFile;
	private List<BasicFile> childFiles = new ArrayList<BasicFile>();

	public BasicFile(String name, String path, long size, boolean isFolder, BasicFile parentFile) {
		this.name = name;
		this.path = path;
		this.size = size;
		this.isFolder = isFolder;
		this.parentFile = parentFile;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public boolean isFolder() {
		return isFolder;
	}

	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	public BasicFile getParentFile() {
		return parentFile;
	}

	public void setParentFile(BasicFile parentFile) {
		this.parentFile = parentFile;
	}

	public List<BasicFile> getChildFiles() {
		return childFiles;
	}

	public void addChildFile(BasicFile childFile) {
		childFiles.add(childFile);
	}

	public void addChildFiles(List<BasicFile> childFiles) {
		childFiles.addAll(childFiles);
	}

	public String toString() {
		return path + " " + size + " " + isFolder;
	}

}
