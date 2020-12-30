package com.base.service;

import com.api.constants.PayConfig;
import com.base.pay.PayPropertySupport;
import com.base.util.CoreConstants;
import com.ijpay.alipay.AliPayApiConfig;
import com.ijpay.alipay.AliPayApiConfigKit;
import com.ijpay.wxpay.WxPayApiConfig;
import com.ijpay.wxpay.WxPayApiConfigKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 敏感词库 初始化缓存
 *
 */
@Component
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class SensitivWordsService {
    private final static Logger logger = LoggerFactory.getLogger(SensitivWordsService.class);
    
    private static final String ENCODING = "UTF-8"; // 字符编码
    
    private static final String PATH = "/static/templates/SensitivWords.txt"; // 词库路径
    
    private static Set<String> keySet = new HashSet<String>();

    
    /**
     * 加载敏感词
     *
     */
    @PostConstruct
    public void load() {
        
        logger.info("[内存数据]-开始加载敏感词数据任务");
        try {
            readSensitiveWordFile();
            initPayConfig();
            logger.info("[内存数据]-敏感词数据任务加载完成");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public void initPayConfig(){
        AliPayApiConfig aliPayApiConfig = AliPayApiConfig.builder()
                .setAppId(PayPropertySupport.getProperty("alipay.appId"))
                .setAliPayPublicKey(PayPropertySupport.getProperty("alipay.publicKey"))
                .setCharset("UTF-8")
                .setPrivateKey(PayPropertySupport.getProperty("alipay.privateKey"))
                .setServiceUrl(PayPropertySupport.getProperty("alipay.serverUrl"))
                .setSignType("RSA2")
                .build();
        AliPayApiConfigKit.putApiConfig(aliPayApiConfig);
    }



    
    private void readSensitiveWordFile() throws Exception {
        Set<String> set = new HashSet<String>();;
        InputStreamReader read = null;
        try {
            if (PATH != null && !"".equals(PATH)) { // 文件流是否存在
                InputStream in = SensitivWordsService.class.getResourceAsStream(PATH);
                read = new InputStreamReader(in, ENCODING);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String txt = null;
                while ((txt = bufferedReader.readLine()) != null) { // 读取文件，将文件内容放入到set中
                    set.add(txt);
                }
                keySet.clear();
                keySet = set;
            } else { // 不存在抛出异常信息
                logger.error("敏感词库文件不存在");
            }
        } catch (Exception e) {
            logger.error("词库加载缓存失败");
        } finally {
            read.close(); // 关闭文件流
        }
    }
    
    /**
     * 过滤敏感词
     * @param info
     * @return
     */
    public String relpSensitivWords(String info) {
        SensitiveWordDfaFilter.initKeyWords(keySet);
        SensitiveWordDfaFilter filter = new SensitiveWordDfaFilter();
        info = filter.replaceSensitiveWord(info, 2, "*");
        return info;
    }

}
