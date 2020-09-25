package com.paidang.dao.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BFile implements Serializable {
    @ApiModelProperty(value = "id")
    private String fileId;

    @ApiModelProperty(value = "归属")
    private String fileBelong;

    @ApiModelProperty(value = "相对路径")
    private String filePath;

    @ApiModelProperty(value = "mine")
    private String fileMinitype;

    @ApiModelProperty(value = "文件名")
    private String fileName;

    @ApiModelProperty(value = "状态")
    private Integer fileState;

    @ApiModelProperty(value = "大小")
    private BigDecimal fileSize;

    @ApiModelProperty(value = "MD5")
    private String fileMd5;

    @ApiModelProperty(value = "创建人")
    private String fileCreater;

    @ApiModelProperty(value = "创建时间")
    private Date fileCreatetime;

    private static final long serialVersionUID = 1L;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileBelong() {
        return fileBelong;
    }

    public void setFileBelong(String fileBelong) {
        this.fileBelong = fileBelong;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileMinitype() {
        return fileMinitype;
    }

    public void setFileMinitype(String fileMinitype) {
        this.fileMinitype = fileMinitype;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileState() {
        return fileState;
    }

    public void setFileState(Integer fileState) {
        this.fileState = fileState;
    }

    public BigDecimal getFileSize() {
        return fileSize;
    }

    public void setFileSize(BigDecimal fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public String getFileCreater() {
        return fileCreater;
    }

    public void setFileCreater(String fileCreater) {
        this.fileCreater = fileCreater;
    }

    public Date getFileCreatetime() {
        return fileCreatetime;
    }

    public void setFileCreatetime(Date fileCreatetime) {
        this.fileCreatetime = fileCreatetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fileId=").append(fileId);
        sb.append(", fileBelong=").append(fileBelong);
        sb.append(", filePath=").append(filePath);
        sb.append(", fileMinitype=").append(fileMinitype);
        sb.append(", fileName=").append(fileName);
        sb.append(", fileState=").append(fileState);
        sb.append(", fileSize=").append(fileSize);
        sb.append(", fileMd5=").append(fileMd5);
        sb.append(", fileCreater=").append(fileCreater);
        sb.append(", fileCreatetime=").append(fileCreatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}