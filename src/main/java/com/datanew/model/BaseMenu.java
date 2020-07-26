package com.datanew.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BASE_MENU")
public class BaseMenu implements java.io.Serializable {
	private String menuid;
	private String menuname;
	private String menutype;
	private String parentid;
	private String menuurl;
	private Integer enable;
	private String menuicon;
	private Integer ordernum;
	
	@Column(name = "ORDERNUM")
	public Integer getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}
	@Column(name = "MENUICON")
	public String getMenuicon() {
		return menuicon;
	}

	public void setMenuicon(String menuicon) {
		this.menuicon = menuicon;
	}

	public BaseMenu() {
	}

	public BaseMenu(String menuid, String menuname, String menutype,
			String parentid, String menuurl,Integer enable) {
		this.menuid = menuid;
		this.menuname = menuname;
		this.menutype = menutype;
		this.parentid = parentid;
		this.menuurl = menuurl;
		this.enable = enable;
	}

	@Id
    @GeneratedValue( generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "base_menu_sequence")
	public String getMenuid() {
		return this.menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	@Column(name = "MENUNAME", length = 100)
	public String getMenuname() {
		return this.menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	@Column(name = "MENUTYPE", length = 6)
	public String getMenutype() {
		return this.menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	@Column(name = "PARENTID", length = 20)
	public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	@Column(name = "MENUURL", length = 100)
	public String getMenuurl() {
		return this.menuurl;
	}

	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}
	
	

	@Column(name = "ENABLE", length = 6)
	public Integer getEnable() {
		return this.enable;
	}
	
	public void setEnable(Integer enable) {
		this.enable = enable;
	}

}