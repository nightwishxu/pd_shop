package com.ruoyi.generator.util;

import java.io.*;
public class JavaBatchSub {
    public static void main(String[] args) throws IOException {
        String path = "D:\\pogram\\XFTV12.0\\Website\\new_last\\Zz_www.czsxtz.cn";		//要遍历的路径
        File file = new File(path);		//获取其file对象
        String [] result = func(file);
        for (int i=0;i<result.length;i++){
            if (result[i] != null ){
                //最后一行是有一个null??
                batchSub(result[i]);
            }
        }

//        batchSub(file);
    }
    public static void batchSub(String path) throws IOException{
        //原有的内容
        String srcStr = "常州市盛翔智能科技有限公司";
        //要替换的内容
        String replaceStr = "解决方案-4.html";
        // 读
        File file = new File(path);
        String name = file.getName();
        FileReader in = new FileReader(file);
        BufferedReader bufIn = new BufferedReader(in);
        // 内存流, 作为临时流
        CharArrayWriter  tempStream = new CharArrayWriter();
        // 替换
        String line = null;
        while ( (line = bufIn.readLine()) != null) {
            // 替换每行中, 符合条件的字符串
            line = line.replaceAll(srcStr, replaceStr);
            // 将该行写入内存
            tempStream.write(line);
            // 添加换行符
            tempStream.append(System.getProperty("line.separator"));
        }
        // 关闭 输入流
        bufIn.close();
        // 将内存中的流 写入 文件
        FileWriter out = new FileWriter(file);
        tempStream.writeTo(out);
        out.close();
        System.out.println("====path:"+path);

    }

    public  static String[] func(File file){
        String [] files = new String[99];//创建一个存99个字符川的数组
        File[] fs = file.listFiles();
        int count = 0;
        for(int i=0;i<fs.length;i++){
            if(fs[i].isDirectory()) { //若是目录，则递归打印该目录下的文件
                func(fs[i]);
            }else if (fs[i].isFile()){      //若是文件，直接打印
                files[count] = fs[i].getAbsolutePath();//把file类型的数组转化成Sting类型的数组
                count++;
            }
        }
        return files;
    }

}
