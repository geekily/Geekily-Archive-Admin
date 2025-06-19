package com.geekive.geekiveArchiveAdmin.vo;

import java.io.Serializable;

public class CommonVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6213382566402137228L;

	String 	menuUid 			= "";
	String 	parentMenuUid 		= "";
	int 	level				= 0;
	int 	order				= 0;
	String 	title				= "";
	String 	path				= "";
	String 	registrationDate 	= "";
	String 	registrationUser 	= "";
	String 	updateDate 			= "";
	String 	updateUser 			= "";
	String 	flagDeleted 		= "";
	
	public String getMenuUid() {
		return menuUid;
	}
	public void setMenuUid(String menuUid) {
		this.menuUid = menuUid;
	}
	public String getParentMenuUid() {
		return parentMenuUid;
	}
	public void setParentMenuUid(String parentMenuUid) {
		this.parentMenuUid = parentMenuUid;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getRegistrationUser() {
		return registrationUser;
	}
	public void setRegistrationUser(String registrationUser) {
		this.registrationUser = registrationUser;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getFlagDeleted() {
		return flagDeleted;
	}
	public void setFlagDeleted(String flagDeleted) {
		this.flagDeleted = flagDeleted;
	}
}
