package com.paidang.domain.pojo;

import io.swagger.annotations.ApiModelProperty;

public class AppVersion {

    //android ios
    @ApiModelProperty(value="系统 0 ios 1 android")
    private Integer system;

    // 0 用户端 1机构端
    @ApiModelProperty(value="user 用户端 org 机构端")
    private String  platForm;

    //下载地址
    @ApiModelProperty(value="下载地址")
    private String url;

    //升级日志
    @ApiModelProperty(value="更新日志")
    private String appChargeLog;

    @ApiModelProperty(value="最低版本")
    private String miniVersion;

    @ApiModelProperty(value="最高版本")
    private String maxVersion;

    @ApiModelProperty(value="是否放出更新提示")
    private Integer updateEnabled;


    public Integer getSystem() {
        return system;
    }

    public void setSystem(Integer system) {
        this.system = system;
    }

    public String getPlatForm() {
        return platForm;
    }

    public void setPlatForm(String platForm) {
        this.platForm = platForm;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppChargeLog() {
        return appChargeLog;
    }

    public void setAppChargeLog(String appChargeLog) {
        this.appChargeLog = appChargeLog;
    }

    public String getMiniVersion() {
        return miniVersion;
    }

    public void setMiniVersion(String miniVersion) {
        this.miniVersion = miniVersion;
    }

    public String getMaxVersion() {
        return maxVersion;
    }

    public void setMaxVersion(String maxVersion) {
        this.maxVersion = maxVersion;
    }

    public Integer getUpdateEnabled() {
        return updateEnabled;
    }

    public void setUpdateEnabled(Integer updateEnabled) {
        this.updateEnabled = updateEnabled;
    }
}
