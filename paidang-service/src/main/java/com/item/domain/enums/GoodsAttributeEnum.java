package com.item.domain.enums;

/**
 * @author: xww
 * @create: 2020-10-28 22:28
 * @description: 商品属性
 * @modified By:
 * @version:
 **/
public enum GoodsAttributeEnum {

    /***
     *   `spec` varchar(100) COLLATE utf8mb4_bin DEFAULT '1' COMMENT '规格',
     *   `weight` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '重量',
     *   `material` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '材质',
     *   `cc_width` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '尺寸--宽',
     *   `cc_height` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '尺寸--高',
     *   `cc_all` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '尺寸（长*宽*高）',
     *   `main_material` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主材',
     *   `other_material` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '辅材',
     *   `cc_length` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '尺寸--长',
     *   `create_year` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创作年代',
     *   `brand` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '品牌',
     *   `new_percent` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '新旧程度',
     *   `theme` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '题材',
     *   `style` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '样式',
     *   `material_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '种地',
     */


    STYLE("style","风格","101"),
    JADE_MATERIAL("jadeMaterial","玉料","101"),
    SORT("sort","类别","101"),
    NATURE("nature","性质","101"),
    WEIGHT("weight","重量","101"),
    CC_ALL("ccAll","尺寸","101"),
    JADE_ARRTIBUTE("jadeAttribute","产状","101"),



    ;




    private String attribute;

    private String name;

    private String belongTo;

    GoodsAttributeEnum(String attribute, String name, String belongTo) {
        this.attribute = attribute;
        this.name = name;
        this.belongTo = belongTo;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getName() {
        return name;
    }


    public String getBelongTo() {
        return belongTo;
    }
}
