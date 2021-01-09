//package com.paidang.utils;
//
//
//import org.bytedeco.javacpp.opencv_core;
//import org.bytedeco.javacv.FFmpegFrameGrabber;
//import org.bytedeco.javacv.Frame;
//import org.bytedeco.javacv.Java2DFrameConverter;
//import org.bytedeco.javacv.OpenCVFrameConverter;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class FrameGrabberKit {
//
//
//    public static void main(String[] args) throws Exception{
////        try {
////            fetchFrame("https://paidang.fsh.bcebos.com/objectStream/2020-11-20/8d2a08f7c1c94a7299a36874310db437.mp4", "D:/test/cs01.jpg");
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
////        getVideoPic(new File("https://paidang.fsh.bcebos.com/objectStream/2020-11-20/8d2a08f7c1c94a7299a36874310db437.mp4"), "D:/test/cs01.jpg");
//        URL url = new URL("https://paidang.fsh.bcebos.com/objectStream/2020-11-20/8d2a08f7c1c94a7299a36874310db437.mp4");
//        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//        fetchPic(conn.getInputStream(), "D:/test/cs01.jpg");
//
//
//    }
//
//
//    /**
//            * 获取指定视频的帧并保存为图片至指定目录
//     * @param videofile  源视频文件路径
//     * @param framefile  截取帧的图片存放路径
//     * @throws Exception
//     * @throws IOException
//     */
//    public static void fetchFrame(String videofile, String framefile)
//            throws Exception, IOException {
//        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile);
//        ff.start();
//        ff.getAudioChannels();
//        String rotate =ff.getVideoMetadata("rotate");//视频的旋转角度
//        int lenght = ff.getLengthInFrames();
//        int i = 0;
//        Frame f = null;
//        while (i < lenght) {
//            // 过滤前5帧，避免出现全黑的图片，依自己情况而定
//            f = ff.grabFrame();
//            if ((i > 5) && (f.image != null)) {
//                break;
//            }
//            opencv_core.IplImage src = null;
//            if(null !=rotate &&rotate.length() > 1) {
//                OpenCVFrameConverter.ToIplImage converter =new OpenCVFrameConverter.ToIplImage();
//                src =converter.convert(f);
//                f =converter.convert(rotate(src, Integer.valueOf(rotate)));
//            }
//            doExecuteFrame(f, framefile);
//            i++;
//        }
//
//    }
//
//
//    public static opencv_core.IplImage rotate(opencv_core.IplImage src, int angle) {
//        opencv_core.IplImage img = opencv_core.IplImage.create(src.height(),src.width(),src.depth(),src.nChannels());
//        opencv_core.cvTranspose(src,img);
//        opencv_core.cvFlip(img,img,angle);
//        return img;
//    }
//
//
//    public static void doExecuteFrame(Frame f,String targetFileName) {
//        if (null ==f ||null ==f.image) {
//            return;
//        }
//        Java2DFrameConverter converter =new Java2DFrameConverter();
//        BufferedImage bi =converter.getBufferedImage(f);
//        File output =new File(targetFileName);
//        try {
//            ImageIO.write(bi,"jpg",output);
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 截取视频获得指定帧的图片
//     *
//     * @param video  源视频文件
//     * @param picPath 截图存放路径
//     */
//    public static void getVideoPic(File video, String picPath) {
//        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
//        try {
//            ff.start();
//            // 截取中间帧图片(具体依实际情况而定)
//            int i = 0;
//            int length = ff.getLengthInFrames();
//            int middleFrame = length / 2;
//            Frame frame = null;
//            while (i < length) {
//                frame = ff.grabFrame();
//                if ((i > middleFrame) && (frame.image != null)) {
//                    break;
//                }
//                i++;
//            }
//            // 截取的帧图片
//            Java2DFrameConverter converter = new Java2DFrameConverter();
//            BufferedImage srcImage = converter.getBufferedImage(frame);
//            int srcImageWidth = srcImage.getWidth();
//            int srcImageHeight = srcImage.getHeight();
//            // 对截图进行等比例缩放(缩略图)
//            int width = 480;
//            int height = (int) (((double) width / srcImageWidth) * srcImageHeight);
//            BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//            thumbnailImage.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
//            File picFile = new File(picPath);
//            ImageIO.write(thumbnailImage, "jpg", picFile);
//            ff.stop();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * 获取指定视频的帧并保存为图片至指定目录
//     * @param file  源视频文件
//     * @param framefile  截取帧的图片存放路径
//     * @throws Exception
//     */
//    public static void fetchPic(File file, String framefile) throws Exception{
//        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(file);
//        ff.start();
//        int lenght = ff.getLengthInFrames();
//
//        File targetFile = new File(framefile);
//        int i = 0;
//        Frame frame = null;
//        while (i < lenght) {
//            // 过滤前5帧，避免出现全黑的图片，依自己情况而定
//            frame = ff.grabFrame();
//            if ((i > 5) && (frame.image != null)) {
//                break;
//            }
//            i++;
//        }
//
//        String imgSuffix = "jpg";
//        if(framefile.indexOf('.') != -1){
//            String[] arr = framefile.split("\\.");
//            if(arr.length>=2){
//                imgSuffix = arr[1];
//            }
//        }
//
//        Java2DFrameConverter converter =new Java2DFrameConverter();
//        BufferedImage srcBi =converter.getBufferedImage(frame);
//        int owidth = srcBi.getWidth();
//        int oheight = srcBi.getHeight();
//        // 对截取的帧进行等比例缩放
//        int width = 800;
//        int height = (int) (((double) width / owidth) * oheight);
//        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//        bi.getGraphics().drawImage(srcBi.getScaledInstance(width, height, Image.SCALE_SMOOTH),0, 0, null);
//        try {
//            ImageIO.write(bi, imgSuffix, targetFile);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        ff.stop();
//    }
//
//
//    public static void fetchPic(InputStream file, String framefile) throws Exception{
//        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(file);
//        ff.start();
//        int lenght = ff.getLengthInFrames();
//
//        File targetFile = new File(framefile);
//        int i = 0;
//        Frame frame = null;
//        while (i < lenght) {
//            // 过滤前5帧，避免出现全黑的图片，依自己情况而定
//            frame = ff.grabFrame();
//            if ((i > 5) && (frame.image != null)) {
//                break;
//            }
//            i++;
//        }
//
//        String imgSuffix = "jpg";
//        if(framefile.indexOf('.') != -1){
//            String[] arr = framefile.split("\\.");
//            if(arr.length>=2){
//                imgSuffix = arr[1];
//            }
//        }
//
//        Java2DFrameConverter converter =new Java2DFrameConverter();
//        BufferedImage srcBi =converter.getBufferedImage(frame);
//        int owidth = srcBi.getWidth();
//        int oheight = srcBi.getHeight();
//        // 对截取的帧进行等比例缩放
//        int width = 800;
//        int height = (int) (((double) width / owidth) * oheight);
//        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//        bi.getGraphics().drawImage(srcBi.getScaledInstance(width, height, Image.SCALE_SMOOTH),0, 0, null);
//        try {
//            ImageIO.write(bi, imgSuffix, targetFile);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        ff.stop();
//    }
//
//}
