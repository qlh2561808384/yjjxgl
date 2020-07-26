package com.datanew.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BASE_OPERATORTOPOST")
public class BaseOperatortopost implements java.io.Serializable {

	// Fields

	private Integer guid;
	private String userid;
	private String postid;

	public BaseOperatortopost() {
	}

	public BaseOperatortopost(String userid, String postid) {
		this.userid = userid;
		this.postid = postid;
	}

	@Id
    @GeneratedValue( generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "base_operatortopost_sequence")
	public Integer getGuid() {
		return this.guid;
	}

	public void setGuid(Integer guid) {
		this.guid = guid;
	}

	@Column(name = "USERID", length = 20)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "POSTID", length = 20)
	public String getPostid() {
		return this.postid;
	}

	public void setPostid(String postid) {
		this.postid = postid;
	}

}