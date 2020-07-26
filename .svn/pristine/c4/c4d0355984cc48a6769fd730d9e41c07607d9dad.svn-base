package com.datanew.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BASE_POSTTOMENU")
public class BasePosttomenu implements java.io.Serializable {

	private Integer guid;
	private String postid;
	private String menuid;

	public BasePosttomenu() {
	}

	public BasePosttomenu(String postid, String menuid) {
		this.postid = postid;
		this.menuid = menuid;
	}

	@Id
    @GeneratedValue( generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "base_posttomenu_sequence")
	public Integer getGuid() {
		return this.guid;
	}

	public void setGuid(Integer guid) {
		this.guid = guid;
	}

	@Column(name = "POSTID", length = 20)
	public String getPostid() {
		return this.postid;
	}

	public void setPostid(String postid) {
		this.postid = postid;
	}

	@Column(name = "MENUID", length = 20)
	public String getMenuid() {
		return this.menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

}