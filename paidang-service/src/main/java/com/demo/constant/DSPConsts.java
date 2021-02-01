package com.demo.constant;

import com.base.util.CoreConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName: UrlConsts
 * @Description: 接口调用地址和证书
 * @Author: chengzi
 * @Date: 2020/7/2 0002 13:02
 * @Version: v1.0 文件初始创建
 */
//@Component
public class DSPConsts {
    /** 同步接口Url(测试) */
    public static  String TX_URL =  CoreConstants.getProperty("tx.url");
    /** 人脸识别接口地址（测试） */
    public static  String LIVENESS_URL  =  CoreConstants.getProperty("liveness.url");
    /** 文件上传接口（测试） */
    public static  String FILE_URL  =  CoreConstants.getProperty("file.url") ;
    /** 评分服务接口（测试） */
    public static  String SCORE_URL  =  CoreConstants.getProperty("score.url");


//    /** 同步接口Url(生产) */
//    public static final String TX_URL_PRO = "https://data.cpcn.com.cn:9443/DSPGateway/InterfaceVII";
//    /** 人脸识别接口地址（生产） */
//    public static final String LIVENESS_URL_PRO = "https://faceb2c.cpcn.com.cn/DSPLivenessWeb/InterfaceVI";
//    /** 文件上传接口（生产） */
//    public static final String FILE_URL_PRO = "https://dspgateway4file.cpcn.com.cn:9443/DSPGateway4File/InterfaceVII";
//    /** 评分服务接口（生产） */
//    public static final String SCORE_URL_PRO = "https://score.cpcn.com.cn:2443/DSPGateway4Score/InterfaceVII";


    /** 私钥文件地址 */
    public static String Keystore  =  CoreConstants.getProperty("Keystore");
    public static String myKeystorePassword  =  CoreConstants.getProperty("myKeystorePassword");
    /** 公钥文件地址 */
    public static String publicKey  =  CoreConstants.getProperty("publicKey") ;


//    // 注意：生产环境证书需要申请并下载制作，并将公钥返还中金      
//    // 在下方空白处 加载申请的pfx文件和密码
//    /** 私钥文件地址(生产) */
//    public static String Keystore_pro = "";
//    public static String myKeystorePassword_pro = "";
//    
//    /** 公钥文件地址（生产） 中金生产公钥*/
//    //注意：生产环境请勿修改此项   务必加载 dspCertificate.cer 
    public static String publicKey_pro = "/project/paidang/certificates/dspCertificate.cer";

    public static String institutionID  =  CoreConstants.getProperty("institution.id") ;

    public static String hostUrl =  CoreConstants.getProperty("host.url") ;
    public static String adminHostUrl =  CoreConstants.getProperty("admin.host.url") ;


    public static String contractPawnTemplateId = CoreConstants.getProperty("contract.pawn.template.id");

    public static String contractRePawnTemplateId = CoreConstants.getProperty("contract.rePawn.template.id");


    public static String CONTRACT_REMARK = "当户承诺：此当物通过合法渠道取得。如当户方未能按照借款合同的约定归还借款，当户授权典当行可以单方面选择直接处置质押物以优先受偿，处置方式为协议折价处理，处置价格以典当时典当行的估值为准，但典当行有权根据市场情况向下浮动30%。当户同意处置过程中发生的各项费用由当户承担。如处置质押物所得价款尚不足偿还典当行借款，并由此导致典当行经济损失，则当户承担偿还责任，典当行可继续对当户未清偿部分的本金、综合费、利息、违约金、罚息等进行追索，由此产生的评估费、律师费用、诉讼费、交通费等以及其它典当行主张权利所支出的必要费用由当户承担。";

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
}

