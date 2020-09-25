//package com.item.task;
//
//import com.base.CoreConstants;
//import com.base.task.annotation.CronTask;
//import com.base.task.annotation.Task;
//import com.base.util.DateUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//@Task
//public class SqlCron {
//    private static Logger logger = LoggerFactory.getLogger(SqlCron.class);
//
//
//    /**
//     * 数据库备份
//     */
//    //每天晚上12点执行一次 0 59 23 * * ?
//    @CronTask("0 59 23 * * ?")
//    public void backup(){
//        try {
//            //mysqldump --opt --host=120.25.144.153 --user=root --password=jackal paidang --result-file=/home/fileServer/paidang/paidang.sql
//            long beginTime=System.currentTimeMillis();
//            String hostIp = CoreConstants.getProperty("jdbc.hostIp");
//            String username = CoreConstants.getProperty("jdbc.username");
//            String password = CoreConstants.getProperty("jdbc.password");
//            String database = CoreConstants.getProperty("jdbc.database");
//            String filepath = CoreConstants.getProperty("file.path");
//            String environment = CoreConstants.getProperty("jdbc.environment");
//            String version = CoreConstants.getProperty("jdbc.version");
//            StringBuffer sb=new StringBuffer();
//            if(version.equals("5.7")){
//                sb.append("mysqlpump ");
//            }else{
//                sb.append("mysqldump --opt ");
//            }
//            sb.append(" --host=");
//            sb.append(hostIp);//mysql主机地址
//            sb.append(" --user=");
//            sb.append(username);//数据库用户名
//            sb.append(" --password=");
//            sb.append(password);//数据库密码
//            sb.append(" ");
//            sb.append(database);//数据库名
//            sb.append(" --result-file=");
//            sb.append(filepath);//保存路径
//            sb.append(DateUtil.format(new Date(),"yyyy-MM-dd")+database+"_"+environment+".sql");//保存名称
//            logger.info("-------------开始执行数据库备份命令:"+sb.toString()+"---------------");
//            Runtime.getRuntime().exec(sb.toString());
//            logger.info("-------------数据库备份成功,耗时："+(System.currentTimeMillis()-beginTime)+"毫秒---------------");
//        }catch(Exception e){
//            logger.error("-------------数据库备份失败---------------",e);
//        }
//    }
//
//}
