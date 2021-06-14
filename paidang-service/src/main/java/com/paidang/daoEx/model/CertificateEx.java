package com.paidang.daoEx.model;

import com.paidang.dao.model.Certificate;
import com.paidang.dao.model.CertificateLog;

import java.util.Date;
import java.util.List;

/**
@author sun
*/
public class CertificateEx extends Certificate {

    private String recordTime;

    private String recordPrice;

    private List<CertificateLog> list;

    private String twoFFile;

    private String twoZFile;

    private String threeFFile;

    private String threeZFile;

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getRecordPrice() {
        return recordPrice;
    }

    public void setRecordPrice(String recordPrice) {
        this.recordPrice = recordPrice;
    }

    public List<CertificateLog> getList() {
        return list;
    }

    public void setList(List<CertificateLog> list) {
        this.list = list;
    }


    public String getTwoFFile() {
        return twoFFile;
    }

    public void setTwoFFile(String twoFFile) {
        this.twoFFile = twoFFile;
    }

    public String getTwoZFile() {
        return twoZFile;
    }

    public void setTwoZFile(String twoZFile) {
        this.twoZFile = twoZFile;
    }

    public String getThreeFFile() {
        return threeFFile;
    }

    public void setThreeFFile(String threeFFile) {
        this.threeFFile = threeFFile;
    }

    public String getThreeZFile() {
        return threeZFile;
    }

    public void setThreeZFile(String threeZFile) {
        this.threeZFile = threeZFile;
    }
}