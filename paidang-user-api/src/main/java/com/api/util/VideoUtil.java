package com.api.util;

import com.base.api.ApiException;
import com.base.util.CoreConstants;
import com.paidang.service.BFileService;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.uuid.UUID;
import org.apache.commons.lang3.StringUtils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author xww
 * @Description:
 * @date 2021/6/26 13:33
 */
@Service
public class VideoUtil {

    @Autowired
    private BFileService fileService;

    //使用javacv
    public String cutImage(String url, Integer number) throws IOException, ApiException {

//        byte[] fileStream = FileUtils.getFileStream(url);
//        File file = new File(fileStream)

        String picPath = StringUtils.EMPTY;
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(FileUtils.getFile(url));
        try {
            ff.start();
            int i = 0;
            int length = ff.getLengthInFrames();
            Frame frame = null;
            while (i < length) {
                frame = ff.grabFrame();
                //截取第几帧图片
                if ((i > number) && (frame.image != null)) {
                    //获取生成图片的路径
                    //执行截图并放入指定位置
                    picPath = doExecuteFrame(frame);
                    break;
                }
                i++;
            }
            ff.stop();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
        return picPath;
    }


    /**
     * 截取缩略图
     *
     * @param f Frame
     */
    private  String doExecuteFrame(Frame f) throws IOException, ApiException {
        String imagemat = "png";
        if (null == f || null == f.image) {
            return null;
        }
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bi = converter.getBufferedImage(f);
        ByteArrayOutputStream bs = new ByteArrayOutputStream();

        ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);

        ImageIO.write(bi, "png", imOut);
        InputStream is = new ByteArrayInputStream(bs.toByteArray());

        Integer available = is.available();
        Long size = available.longValue();

        String fileName = UUID.randomUUID().toString() + ".png";
//        StringBuilder stringBuilder = new StringBuilder(OssConstant.objectNamePrefix);
//        MtpOssUtil.OssConfig ossConfig = new MtpOssUtil().new OssConfig(OssConstant.bucketName, OssConstant.endpoint, OssConstant.accessKeyId, OssConstant.accessKeySecret);
        String filePath =fileService.uploadFile(bs.toByteArray(),fileName,"user-api");
//        String filePath = MtpOssUtil.upload(ossConfig, fileName, is, size);
        return CoreConstants.BOS_URL + filePath;
    }


    //文件转化
    private static File MultipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 若需要防止生成的临时文件重复,可以在文件名后添加随机码

        try {
            File file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
