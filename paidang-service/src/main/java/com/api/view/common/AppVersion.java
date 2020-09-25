package com.api.view.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AppVersion {

	@ApiModelProperty(value="版本")
	private double version;
	@ApiModelProperty(value="下载地址")
	private String url;
	@ApiModelProperty(value="1强制更新0不强制")
	private int isFlag;
	@ApiModelProperty(value="更新内容")
	private String info;
	@ApiModelProperty(value="应用下载地址")
	private String apkFile;

	public String getApkFile() {
		return apkFile;
	}

	public void setApkFile(String apkFile) {
		this.apkFile = apkFile;
	}

	public double getVersion() {
		return version;
	}
	public void setVersion(double version) {
		this.version = version;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getIsFlag() {
		return isFlag;
	}
	public void setIsFlag(int isFlag) {
		this.isFlag = isFlag;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

}
