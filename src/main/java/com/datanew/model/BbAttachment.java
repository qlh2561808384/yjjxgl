package com.datanew.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "BB_ATTACHMENT")
public class BbAttachment {
    private Long guid;
    private String filename;
	private byte[] filecontent;
	private Date uploaddate;
	private Integer reportuserid;
	@Id
    @GeneratedValue( generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "bb_attachment_sequence")
    public Long getGuid() {
		return guid;
	}

	public void setGuid(Long guid) {
		this.guid = guid;
	}

	
	@Column(name = "FILENAME",length=200)
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	@Column(name = "FILECONTENT")
	public byte[] getFilecontent() {
		return filecontent;
	}

	public void setFilecontent(byte[] filecontent) {
		this.filecontent = filecontent;
	}
	@Column(name = "UPLOADDATE")
	public Date getUploaddate() {
		return uploaddate;
	}

	public void setUploaddate(Date uploaddate) {
		this.uploaddate = uploaddate;
	}
	@Column(name = "REPORTUSERID")
	public Integer getReportuserid() {
		return reportuserid;
	}

	public void setReportuserid(Integer reportuserid) {
		this.reportuserid = reportuserid;
	}

   
   
}
