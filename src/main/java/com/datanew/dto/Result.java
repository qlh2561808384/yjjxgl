package com.datanew.dto;

import com.datanew.util.FileInfo;

public class Result {
	public boolean success=false;
	public Object content;
	private String msg = "",info = "",error = "",returnId="",strsuccess="",reportFileName="",bdoFileName="";
	private FileInfo fileInfo = null;
	
	public Result() {

	}

	public Result(boolean success, Object content) {
		super();
		this.success = success;
		this.content = content;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public Object getContent() {
		return content;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	public String getBdoFileName() {
		return bdoFileName;
	}
	public void setBdoFileName(String bdoFileName) {
		this.bdoFileName = bdoFileName;
	}
	public String getReportFileName() {
		return reportFileName;
	}
	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}
	
	public String getStrsuccess() {
		return strsuccess;
	}

	public void setStrsuccess(String strsuccess) {
		this.strsuccess = strsuccess;
	}
}
