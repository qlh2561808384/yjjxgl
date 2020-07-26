package com.datanew.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BASE_USER_ROLE")
public class BaseUserRole implements Serializable {
	
    public Integer guid;
	public Long roleId;
	public Long userId;
	
	@Id
    @GeneratedValue( generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "base_user_role_sequence")
	public Integer getGuid() {
		return guid;
	}

	public void setGuid(Integer guid) {
		this.guid = guid;
	}
	@Column(name = "roleId",length = 20)
	public Long getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	@Column(name = "userId",length = 20)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	

}
