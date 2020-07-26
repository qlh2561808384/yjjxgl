package com.datanew.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BASE_MODULE")
public class BaseModule{
	
	private Long guId;      	//主键
	private String code;		//模版编码
	private String name; 		//模版名称
	private String content;     //模版内容
	private Date writeTime;     //创建时间
	
	@Id
    @GeneratedValue( generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "base_sms_module_sequence")
	public Long getGuId() {
		return guId;
	}

	public void setGuId(Long guId) {
		this.guId = guId;
	}
	@Column(name = "code",length=30)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@Column(name = "content",length=500)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "writeTime")
	public Date getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}
	@Column(name = "name",length=50)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}

