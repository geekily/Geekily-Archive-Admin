package com.geekive.geekiveArchiveAdmin.vo;

import java.io.Serializable;

import com.geekive.geekiveArchiveAdmin.common.Util;

public class OptionVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8996330793211529175L;

	/* common */
	String label			= "";
	String optionLabel 		= "";
	String name				= "";
	
	String id				= "";
	String key				= "";
	String value			= "";
	
	String readonly			= "";
	String hide				= "";
	
	String data				= "";
	String selectData		= "";
	String textData			= "";
	String userUid			= "";
	
	/* search */
	String selectName 		= "";
	String textName			= "";
	String maxlength		= "";
	
	/* text */
	String placeholder		= "";
	
	/* checkbox */
	String all				= "";
	String checkboxKey		= "";
	String checkboxValue	= "";
	String checkboxLabel	= "";
	
	/* radio */
	String radioKey			= "";
	String radioValue		= "";
	String radioLabel		= "";
	
	/* code */
	String codeType			= "";
	String codeKey			= "";
	String codeValue		= "";
	String codeLabel		= "";
	
	/* ckeditor */
	String width			= "";
	String height			= "";
	
	/* file */
	String accept			= "";
	
	/* image */
	String crop				= "";
	String preview			= "";
	String fileUid			= "";
	String originalName		= "";
	String urlPath			= "";
	
	/* test */
	String title			= "";
	String content			= "";
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getOptionLabel() {
		return this.label;
	}
	public void setOptionLabel(String optionLabel) {
		this.optionLabel = optionLabel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		if(Util.isNotEmpty(this.codeKey)) {
			this.key = this.codeKey;
		}
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		if(Util.isNotEmpty(this.codeLabel)) {
			this.value = this.codeLabel;
		}
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean getReadonly() {
		if(this.readonly.equals("true")){
			return true;
		}else {
			return false;
		}
	}
	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}
	public Boolean getHide() {
		if(this.hide.equals("true")){
			return true;
		}else {
			return false;
		}
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getSelectData() {
		return selectData;
	}
	public void setSelectData(String selectData) {
		this.selectData = selectData;
	}
	public String getTextData() {
		return textData;
	}
	public void setTextData(String textData) {
		this.textData = textData;
	}
	public String getUserUid() {
		return userUid;
	}
	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}
	public void setHide(String hide) {
		this.hide = hide;
	}
	public String getSelectName() {
		return selectName;
	}
	public void setSelectName(String selectName) {
		this.selectName = selectName;
	}
	public String getTextName() {
		return textName;
	}
	public void setTextName(String textName) {
		this.textName = textName;
	}
	public String getMaxlength() {
		return maxlength;
	}
	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}
	public String getPlaceholder() {
		return placeholder;
	}
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	public Boolean getAll() {
		if(this.all.equals("true") || Util.isEmpty(this.all)){
			return true;
		}else {
			return false;
		}
	}
	public void setAll(String all) {
		this.all = all;
	}
	public String getCheckboxKey() {
		if(Util.isNotEmpty(this.codeKey)) {
			this.checkboxKey = this.codeKey;
		}
		return checkboxKey;
	}
	public void setCheckboxKey(String checkBoxKey) {
		this.checkboxKey = checkBoxKey;
	}
	public String getCheckboxValue() {
		if(Util.isNotEmpty(this.codeValue)) {
			this.checkboxValue = this.codeValue;
		}
		return checkboxValue;
	}
	public void setCheckboxValue(String checkBoxValue) {
		this.checkboxValue = checkBoxValue;
	}
	public String getCheckboxLabel() {
		if(Util.isNotEmpty(this.codeLabel)) {
			this.checkboxLabel = this.codeLabel;
		}
		return checkboxLabel;
	}
	public void setCheckboxLabel(String checkBoxLabel) {
		this.checkboxLabel = checkBoxLabel;
	}
	public String getRadioKey() {
		if(Util.isNotEmpty(this.codeKey)) {
			this.radioKey = this.codeKey;
		}
		return radioKey;
	}
	public void setRadioKey(String radioKey) {
		this.radioKey = radioKey;
	}
	public String getRadioValue() {
		if(Util.isNotEmpty(this.codeValue)) {
			this.radioValue = this.codeValue;
		}
		return radioValue;
	}
	public void setRadioValue(String radioValue) {
		this.radioValue = radioValue;
	}
	public String getRadioLabel() {
		if(Util.isNotEmpty(this.codeLabel)) {
			this.radioLabel = this.codeLabel;
		}
		return radioLabel;
	}
	public void setRadioLabel(String radioLabel) {
		this.radioLabel = radioLabel;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getCodeKey() {
		return codeKey;
	}
	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	public String getCodeLabel() {
		return codeLabel;
	}
	public void setCodeLabel(String codeLabel) {
		this.codeLabel = codeLabel;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getAccept() {
		return accept;
	}
	public void setAccept(String accept) {
		this.accept = accept;
	}
	public Boolean getCrop() {
		if(this.crop.equals("true")){
			return true;
		}else {
			return false;
		}
	}
	public void setCrop(String crop) {
		this.crop = crop;
	}
	public Boolean getPreview() {
		if(this.preview.equals("true")){
			return true;
		}else {
			return false;
		}
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getFileUid() {
		return fileUid;
	}
	public void setFileUid(String fileUid) {
		this.fileUid = fileUid;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getUrlPath() {
		return urlPath;
	}
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
