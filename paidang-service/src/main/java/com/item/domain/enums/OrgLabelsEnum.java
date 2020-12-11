package com.item.domain.enums;

import org.apache.commons.lang.StringUtils;

public enum OrgLabelsEnum {

    PERSONAL("1","个人认证"),
    ENTERPRISE("2","企业认证"),
    OPTIMIZED_STORE("3","优选好店"),
    STORE_DEPOSIT("4","店铺保证金")
    ;

    private String label;

    private String labelName;

    OrgLabelsEnum(String label, String labelName) {
        this.label = label;
        this.labelName = labelName;
    }


    public String getLabel() {
        return label;
    }

    public String getLabelName() {
        return labelName;
    }

    public static OrgLabelsEnum getName(String label) {
        if (StringUtils.isBlank(label)) {
            return null;
        }
        OrgLabelsEnum result = null;
        for (OrgLabelsEnum tmp : OrgLabelsEnum.values()) {
            if (tmp.getLabel().equals(label)) {
                result = tmp;
                break;
            }
        }
        return result;
    }
}
