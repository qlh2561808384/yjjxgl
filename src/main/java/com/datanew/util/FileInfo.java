package com.datanew.util;

public class FileInfo {
	private String id;
	private String name;
	private String Alias ;//<Alias>�ļ�����</Alias>
	private String TypeName; //<TypeName>hte</TypeName>
	private String Version;//<Version>5.2</Version>
	private String ParentId;//<ParentId>0</ParentId>
	private String ParamsStr;//<ParamsStr>...(�ļ�������Ϣ�����needFileParam���������Ƿ񷵻�)</ParamsStr>
	private String Descript;// <Descript>������Ϣ</Descript>
	private String source;
	private String target;
	private String ignore;
	private String synchro;
	private String parentName;
	private String type;
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIgnore() {
		return ignore;
	}
	public void setIgnore(String ignore) {
		this.ignore = ignore;
	}
	public String getSynchro() {
		return synchro;
	}
	public void setSynchro(String synchro) {
		this.synchro = synchro;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return Alias;
	}
	public void setAlias(String alias) {
		Alias = alias;
	}
	public String getTypeName() {
		return TypeName;
	}
	public void setTypeName(String typeName) {
		TypeName = typeName;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getParentId() {
		return ParentId;
	}
	public void setParentId(String parentId) {
		ParentId = parentId;
	}
	public String getParamsStr() {
		return ParamsStr;
	}
	public void setParamsStr(String paramsStr) {
		ParamsStr = paramsStr;
	}
	public String getDescript() {
		return Descript;
	}
	public void setDescript(String descript) {
		Descript = descript;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	

}
