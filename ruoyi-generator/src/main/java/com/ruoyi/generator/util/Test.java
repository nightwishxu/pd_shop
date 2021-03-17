package com.ruoyi.generator.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;


public class Test {
    /**
     * @author itmyhome
     */
    public static void main(String[] args) {
        File f = new File("D:\\pogram\\XFTV12.0\\Website\\new_last\\Zz_www.czsxtz.cn");
        print(f, 0);
    }

    /**
     * 遍历目录
     *
     * @param f
     * @param len
     */
    public static void print(File f, int len) {
        File[] file = f.listFiles();

        for (int i = 0; i < file.length; i++) {
            if (file[i].isDirectory()) { //推断是否目录
                print(file[i], len + 1);
            }

            // 为防止输出文件覆盖源文件，所以更改输出盘路径 也可自行设置其它路径
            File outPath = new File(file[i].getParent().replace("D:", "E:"));
            File readfile = new File(file[i].getAbsolutePath());

            if (!readfile.isDirectory()) {
                String filename = readfile.getName(); // 读到的文件名称
                String absolutepath = readfile.getAbsolutePath(); // 文件的绝对路径
                readFile(absolutepath, filename, i, outPath); // 调用 readFile
            }
        }
    }

    /**
     * 读取目录下的文件
     *
     * @return
     */
    public static void readFile(String absolutepath, String filename,
                                int index, File outPath) {
        try {
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(absolutepath), "UTF-8")); // 数据流读取文件
            boolean flag = false;
            if (filename.endsWith("html") || filename.endsWith("htm")){
                flag = true;
            }

            if (true){

                StringBuffer strBuffer = new StringBuffer();
                String newStr = "index.html\">网站首页";
                String oldStr = "stopped.html\">网站首页";

                for (String temp = null; (temp = bufReader.readLine()) != null;
                     temp = null) {

                    if ((temp.indexOf(oldStr) != -1) &&
                            (temp.indexOf(oldStr) != -1)) { // 推断当前行是否存在想要替换掉的字符
                        temp = temp.replace(oldStr, newStr); // 此处进行替换
                    }
                    strBuffer.append(temp);
                    strBuffer.append(System.getProperty("line.separator")); // 换行符



                }

                bufReader.close();

                if (outPath.exists() == false) { // 检查输出目录是否存在，若不存在先创建
                    outPath.mkdirs();
                    System.out.println("已成功创建输出目录：" + outPath);
                }

                PrintWriter printWriter = new PrintWriter(outPath + "\\" +
                        filename, "UTF-8"); // 替换后输出文件路径
                printWriter.write(strBuffer.toString().toCharArray()); //又一次写入
                printWriter.flush();
                printWriter.close();
                System.out.println("第 " + (index + 1) + " 个文件   " + absolutepath +
                        "  已成功输出到    " + outPath + "\\" + filename);
            }else {
                FileChannel inputChannel = null;
                FileChannel outputChannel = null;

                inputChannel = new FileInputStream(new File(absolutepath)).getChannel();
                outputChannel = new FileInputStream(outPath).getChannel();
                outputChannel.transferFrom(inputChannel,0,inputChannel.size());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
