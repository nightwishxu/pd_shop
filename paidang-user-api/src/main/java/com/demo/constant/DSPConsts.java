//package com.demo.constant;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
///**
// * @ClassName: UrlConsts
// * @Description: 接口调用地址和证书
// * @Author: chengzi
// * @Date: 2020/7/2 0002 13:02
// * @Version: v1.0 文件初始创建
// */
//@Component
//public class DSPConsts {
//    /** 同步接口Url(测试) */
//    public static  String TX_URL;
//    /** 人脸识别接口地址（测试） */
//    public static  String LIVENESS_URL;
//    /** 文件上传接口（测试） */
//    public static  String FILE_URL ;
//    /** 评分服务接口（测试） */
//    public static  String SCORE_URL;
//
//
////    /** 同步接口Url(生产) */
////    public static final String TX_URL_PRO = "https://data.cpcn.com.cn:9443/DSPGateway/InterfaceVII";
////    /** 人脸识别接口地址（生产） */
////    public static final String LIVENESS_URL_PRO = "https://faceb2c.cpcn.com.cn/DSPLivenessWeb/InterfaceVI";
////    /** 文件上传接口（生产） */
////    public static final String FILE_URL_PRO = "https://dspgateway4file.cpcn.com.cn:9443/DSPGateway4File/InterfaceVII";
////    /** 评分服务接口（生产） */
////    public static final String SCORE_URL_PRO = "https://score.cpcn.com.cn:2443/DSPGateway4Score/InterfaceVII";
//
//
//    /** 私钥文件地址(测试) */
//    public static String Keystore;
//    public static String myKeystorePassword;
//    /** 公钥文件地址（测试） */
//    public static String publicKey ;
//
//
////    // 注意：生产环境证书需要申请并下载制作，并将公钥返还中金
////    // 在下方空白处 加载申请的pfx文件和密码
////    /** 私钥文件地址(生产) */
////    public static String Keystore_pro = "";
////    public static String myKeystorePassword_pro = "";
////
////    /** 公钥文件地址（生产） 中金生产公钥*/
////    //注意：生产环境请勿修改此项   务必加载 dspCertificate.cer
////    public static String publicKey_pro = "E:/work_space_wb/paidang/certificates/dspCertificate.cer";
//
//    public static String institutionID;
//
//    public static String hostUrl;
//
//
//    @Value("${tx.url}")
//    public  void setTxUrl(String txUrl) {
//        TX_URL = txUrl;
//    }
//
//    @Value("${liveness.url}")
//    public  void setLivenessUrl(String livenessUrl) {
//        LIVENESS_URL = livenessUrl;
//    }
//
//    @Value("${file.url}")
//    public  void setFileUrl(String fileUrl) {
//        FILE_URL = fileUrl;
//    }
//
//    @Value("${score.url}")
//    public  void setScoreUrl(String scoreUrl) {
//        SCORE_URL = scoreUrl;
//    }
//
//    @Value("${Keystore}")
//    public  void setKeystore(String keystore) {
//        Keystore = keystore;
//    }
//
//    @Value("${myKeystorePassword}")
//    public  void setMyKeystorePassword(String myKeystorePassword) {
//        DSPConsts.myKeystorePassword = myKeystorePassword;
//    }
//
//
//    @Value("${publicKey}")
//    public  void setPublicKey(String publicKey) {
//        DSPConsts.publicKey = publicKey;
//    }
//
//    @Value("${institution.id}")
//    public  void setInstitutionID(String institutionID) {
//        DSPConsts.institutionID = institutionID;
//    }
//
//    @Value("${host.url}")
//    public  void setHostUrl(String hostUrl) {
//        DSPConsts.hostUrl = hostUrl;
//    }
//}
//
