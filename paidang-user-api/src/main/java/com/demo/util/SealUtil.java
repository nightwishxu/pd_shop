package com.demo.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Arrays;

public class SealUtil {

    /**
     * 输出图片的公用方法
     *
     * @param message
     *            弧度上的字
     * @param messageFont
     *            字体
     * @param centerName
     *            中间要显示的名字
     * @param centFont
     *            字体
     * @param centtopdown
     *            名字上下移动大小
     * @param centleftright
     *            名字左右移动大小
     * @param bottomName
     *            中间底部名字
     * @param btFont
     *            字体
     * @param bttopdown
     *            中间底部名字上下移动大小
     * @param btleftright
     *            中间底部名字左右移动大小
     * @param bottomName1
     *            中间底部名字1
     * @param bt1Font
     *            字体
     * @param bt1topdown
     *            中间底部名字1上下移动大小
     * @param bt1leftright
     *            中间底部名字1左右移动大小
     * @param width
     *            中间字体的宽度
     * @param height
     *            中间字体的高度
     * @param type
     *            1 带五星 0 不带五星
     * @return
     * @throws Exception
     * @throws FileNotFoundException
     * @throws URISyntaxException
     */
    public static byte[] getCirclePng(String message, String messageFont, String centerName, String centFont, Color color, int centtopdown, int centleftright,
            String bottomName, String btFont, int bttopdown, int btleftright, String bottomName1, String bt1Font, int bt1topdown, int bt1leftright, int width,
            int height, int type) throws Exception {
        // LOGGER.debug("message: {}", message);

        BufferedImage image = null;
        if (message != null) {
            final int CENTERX = 260;
            final int CENTERY = 260;
            final int radius = 230;

            String path = null;
            if (color == Color.RED) {
                path = "./png/seal_red.png";
            } else if (color == Color.BLUE) {
                path = "./png/seal_blue.png";
            } else if (color == Color.BLACK) {
                path = "./png/seal_black.png";
            } else {
                if (type == 1) {
                    path = "./png/seal_red.png";
                } else {
                    path = "./png/model.png";
                }
            }

            image = ImageIO.read(new FileInputStream(path));
            Graphics2D g2 = image.createGraphics();
            g2.setColor(color);
            g2.setFont(new Font(centFont, Font.BOLD, 40));
            if (centerName != null) {
                g2.drawString(centerName, 380 / 2 + centleftright, 380 + centtopdown);
            }
            g2.setFont(new Font(btFont, Font.BOLD, 20));
            if (bottomName != null) {
                g2.drawString(bottomName, 380 / 2 + 35 + btleftright, 380 + 20 + bttopdown);
            }
            g2.setFont(new Font(bt1Font, Font.BOLD, 15));
            if (bottomName1 != null) {
                g2.drawString(bottomName1, 380 / 2 + 45 + bt1leftright, 380 + 35 + bt1topdown);
            }
            String[] messagesTmp = message.split("", 0);

            String[] messages = null;
            if (CommonUtil.isEmpty(messagesTmp[0])) {
                messages = new String[messagesTmp.length - 1];
                System.arraycopy(messagesTmp, 1, messages, 0, messagesTmp.length - 1);
            } else {
                messages = messagesTmp;
            }
            // LOGGER.debug("messages: {}", Arrays.toString(messages));

            int length = messages.length;
            // LOGGER.debug("messages length: {}", length);
            int fontsize = 50;
            if (length < 5) {
                fontsize = 70;
            } else if (length < 10) {
                fontsize = 60;
            } else if (length < 20) {
                fontsize = 50;
            } else if (length < 30) {
                fontsize = 40;
            } else if (length < 40) {
                fontsize = 30;
            } else {
                messages = Arrays.copyOfRange(messages, 0, 40);
                length = messages.length;
                fontsize = 25;
            }

            Font f = new Font(messageFont, Font.BOLD, fontsize);
            FontRenderContext context = g2.getFontRenderContext();
            Rectangle2D bounds = f.getStringBounds(message, context);
            // 字符宽度＝字符串长度/字符数
            double char_interval = (bounds.getWidth() / length);
            // 上坡度
            double ascent = -bounds.getY();
            int first = 0, second = 0;
            boolean odd = false;
            if (length % 2 == 1) {
                first = (length - 1) / 2;
                odd = true;
            } else {
                first = (length) / 2 - 1;
                second = (length) / 2;
                odd = false;
            }
            double radius2 = radius - ascent;
            double x0 = CENTERX;
            double y0 = CENTERY - radius + ascent;
            // 旋转角度
            double a = 2 * Math.asin(char_interval / (2 * radius2));
            if (odd) {
                g2.setFont(f);
                g2.drawString(messages[first], (float) (x0 - char_interval / 2), (float) y0);
                // LOGGER.debug("messages[first]: {}", messages[first]);
                // 中心点的左边
                for (int i = first - 1; i > -1; i--) {
                    double aa = (first - i) * a;
                    double ax = radius2 * Math.sin(aa);
                    double ay = radius2 - radius2 * Math.cos(aa);
                    AffineTransform transform = AffineTransform.getRotateInstance(-aa);// ,x0 + ax, y0 + ay);
                    Font f2 = f.deriveFont(transform);
                    g2.setFont(f2);
                    g2.drawString(messages[i], (float) (x0 - ax - char_interval / 2 * Math.cos(aa)) - 3, (float) (y0 + ay + char_interval / 2 * Math.sin(aa)));
                    // LOGGER.debug("messages[{}]: {}", i, messages[i]);
                }
                // 中心点的右边
                for (int i = first + 1; i < length; i++) {
                    double aa = (i - first) * a;
                    double ax = radius2 * Math.sin(aa);
                    double ay = radius2 - radius2 * Math.cos(aa);
                    AffineTransform transform = AffineTransform.getRotateInstance(aa);
                    Font f2 = f.deriveFont(transform);
                    g2.setFont(f2);
                    g2.drawString(messages[i], (float) (x0 + ax - char_interval / 2 * Math.cos(aa)) - 3, (float) (y0 + ay - char_interval / 2 * Math.sin(aa)));
                    // LOGGER.debug("messages[{}]: {}", i, messages[i]);
                }
            } else {
                // 中心点的左边
                for (int i = first; i > -1; i--) {
                    double aa = (first - i + 0.5) * a;
                    double ax = radius2 * Math.sin(aa);
                    double ay = radius2 - radius2 * Math.cos(aa);
                    AffineTransform transform = AffineTransform.getRotateInstance(-aa);// ,x0 + ax, y0 + ay);
                    Font f2 = f.deriveFont(transform);
                    g2.setFont(f2);
                    g2.drawString(messages[i], (float) (x0 - ax - char_interval / 2 * Math.cos(aa)) - 3, (float) (y0 + ay + char_interval / 2 * Math.sin(aa)));
                    // LOGGER.debug("messages[{}]: {}", i, messages[i]);
                }
                // 中心点的右边
                for (int i = second; i < length; i++) {
                    double aa = (i - second + 0.5) * a;
                    double ax = radius2 * Math.sin(aa);
                    double ay = radius2 - radius2 * Math.cos(aa);
                    AffineTransform transform = AffineTransform.getRotateInstance(aa);// ,x0 + ax, y0 + ay);
                    Font f2 = f.deriveFont(transform);
                    g2.setFont(f2);
                    g2.drawString(messages[i], (float) (x0 + ax - char_interval / 2 * Math.cos(aa)) - 3, (float) (y0 + ay - char_interval / 2 * Math.sin(aa)));
                    // LOGGER.debug("messages[{}]: {}", i, messages[i]);
                }
            }
            g2.dispose();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", byteArrayOutputStream);
        return ImageUtil.resizeImage(byteArrayOutputStream.toByteArray(), width, height);
    }

    public static byte[] formatImageAddBusinessCode(byte[] imageData, int imageWidth, int imageHeight, String businessCode, String businessCodeStyle)
            throws Exception {
        BufferedImage image = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] data = ImageUtil.resizeImage(imageData, 200, 200);
            image = ImageIO.read(new ByteArrayInputStream(data));

            Graphics2D g2 = image.createGraphics();
            g2.setColor(Color.RED);
            int length = businessCode.length();
            int size = Integer.parseInt(businessCodeStyle);

            length *= size;
            length = (int) (100 - 0.3 * length);

            Font font = new Font("宋体", Font.BOLD, size);
            g2.setFont(font);

            FontRenderContext context = g2.getFontRenderContext();
            Rectangle2D bounds = font.getStringBounds(businessCode, context);
            bounds = font.getStringBounds(businessCode, context);

            g2.drawString(businessCode, (int) (100 - bounds.getWidth() / 2), 100);
            g2.dispose();

            ImageIO.write(image, "png", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ImageUtil.resizeImage(byteArrayOutputStream.toByteArray(), imageWidth, imageHeight);
    }

    public static byte[] getSquarePng(String message, int width, int height, String fontFamily, int fontSize, int type, Color color) throws Exception {
        BufferedImage buffImg = null;
        if (message != null) {
            buffImg = new BufferedImage(fontSize * 2, fontSize * 2, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = buffImg.createGraphics();
            buffImg = g2.getDeviceConfiguration().createCompatibleImage(fontSize * 2, fontSize * 2, Transparency.TRANSLUCENT);
            g2 = buffImg.createGraphics();
            g2.setColor(color);
            g2.setFont(new Font(fontFamily, Font.LAYOUT_LEFT_TO_RIGHT, fontSize));

            String newMessage = null;
            if (message.length() == 1) {
                newMessage = message + "之印章";
            } else if (message.length() == 2) {
                newMessage = message + "之章";
            } else if (message.length() == 3) {
                newMessage = message + "章";
            } else if (message.length() > 3) {
                newMessage = message.substring(0, 4);
            }

            char[] charArray = newMessage.toCharArray();
            if (type == 0) {
                // 竖向
                g2.drawString(new String(new char[] { charArray[0] }), fontSize, fontSize - fontSize / 8);
                g2.drawString(new String(new char[] { charArray[1] }), fontSize, fontSize * 2 - fontSize / 8);
                g2.drawString(new String(new char[] { charArray[2] }), 0, fontSize - fontSize / 8);
                g2.drawString(new String(new char[] { charArray[3] }), 0, fontSize * 2 - fontSize / 8);
            } else {
                // 横向
                g2.drawString(new String(new char[] { charArray[1] }), fontSize, fontSize - fontSize / 8);
                g2.drawString(new String(new char[] { charArray[3] }), fontSize, fontSize * 2 - fontSize / 8);
                g2.drawString(new String(new char[] { charArray[0] }), 0, fontSize - fontSize / 8);
                g2.drawString(new String(new char[] { charArray[2] }), 0, fontSize * 2 - fontSize / 8);
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(buffImg, "png", byteArrayOutputStream);

        return ImageUtil.resizeImage(byteArrayOutputStream.toByteArray(), width, height);
    }

    public static byte[] getRectanglePng(String message, int width, int height, int fontSize, Color color, String fontFamily) throws Exception {
        BufferedImage buffImg = null;
        if (message != null) {
            if (message.length() > 15) {
                message = message.substring(0, 15);
            }
            String[] msg = message.split("##");
            int maxLength = msg[0].length();
            for (int i = 0; i < msg.length; i++) {
                if (msg[i].length() > maxLength)
                    maxLength = msg[i].length();
            }

            buffImg = new BufferedImage(maxLength * fontSize + maxLength * 2, msg.length * fontSize, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = buffImg.createGraphics();
            buffImg = g2.getDeviceConfiguration().createCompatibleImage(maxLength * fontSize + maxLength * 2, msg.length * fontSize, Transparency.TRANSLUCENT);
            g2 = buffImg.createGraphics();
            g2.setColor(color);
            g2.setFont(new Font(fontFamily, Font.LAYOUT_LEFT_TO_RIGHT, fontSize));
            for (int j = 0; j < msg.length; j++) {
                char[] charArray = msg[j].toCharArray();
                long lengthPerChar = maxLength * fontSize / msg[j].length();
                for (int i = 0; i < msg[j].length(); i++) {
                    g2.drawString(new String(new char[] { charArray[i] }), 2 + fontSize * i + i * (lengthPerChar - fontSize) * 3 / 2,
                            fontSize - fontSize / 8 + fontSize * j);
                }
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(buffImg, "png", byteArrayOutputStream);
        return ImageUtil.resizeImage(byteArrayOutputStream.toByteArray(), width, height);
    }

    public static byte[] getRectanglePng(String message, Color color, String fontFamily) throws Exception {
        BufferedImage buffImg = null;
        int width = 0;
        int height = 40;
        int fontSize = 100;
        if (message != null) {
            int length = message.length();
            if (length > 15) {
                width = 600;
            } else {
                width = length * 40;
            }
            buffImg = new BufferedImage(length * fontSize + length * 2, fontSize, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = buffImg.createGraphics();
            buffImg = g2.getDeviceConfiguration().createCompatibleImage(length * fontSize + length * 2, fontSize, Transparency.TRANSLUCENT);
            g2 = buffImg.createGraphics();
            g2.setColor(color);
            g2.setFont(new Font(fontFamily, Font.LAYOUT_LEFT_TO_RIGHT, fontSize));
            char[] charArray = message.toCharArray();
            for (int i = 0; i < message.length(); i++) {
                g2.drawString(new String(new char[] { charArray[i] }), 2 + fontSize * i, fontSize - fontSize / 8);
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(buffImg, "png", byteArrayOutputStream);

        return ImageUtil.resizeImage(byteArrayOutputStream.toByteArray(), width, height);
    }

    public static byte[] getCirclePngToByte(String imageName, String imageName2, int height, int width) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedImage buffImg = null;

        try {
            final int CENTERX = 260;
            final int CENTERY = 260;
            final int radius = 230;

            String path = "./data" + File.separator + "starmodel.png";
            buffImg = ImageIO.read(new FileInputStream(path));
            Graphics2D g2 = buffImg.createGraphics();// 得到图形上下文
            g2.setColor(Color.RED); // 设置画笔颜色
            // 设置字体
            if (imageName2 != null) {
                Font font = new Font("宋体", Font.BOLD, 40);
                g2.setFont(font);
                FontRenderContext context = g2.getFontRenderContext();
                Rectangle2D bounds = font.getStringBounds(imageName2, context);
                int length = imageName2.length();
                bounds = font.getStringBounds(imageName2, context);
                int char_interval = (int) (bounds.getWidth() / length);
                g2.drawString(imageName2, CENTERX - char_interval * length / 2, 400);
            }
            // 根据输入字符串得到字符数组
            String[] messages2 = imageName.split("", 0);
            String[] messages = new String[messages2.length - 1];
            System.arraycopy(messages2, 1, messages, 0, messages2.length - 1);
            // 输入的字数
            int ilength = messages.length;
            Font f = new Font("宋体", Font.BOLD, 60);
            FontRenderContext context = g2.getFontRenderContext();
            Rectangle2D bounds = f.getStringBounds(imageName, context);
            // 字符宽度＝字符串长度/字符数
            double char_interval = (bounds.getWidth() / ilength);
            // 上坡度
            double ascent = -bounds.getY();
            int first = 0, second = 0;
            boolean odd = false;
            if (ilength % 2 == 1) {
                first = (ilength - 1) / 2;
                odd = true;
            } else {
                first = (ilength) / 2 - 1;
                second = (ilength) / 2;
                odd = false;
            }
            double radius2 = radius - ascent;
            double x0 = CENTERX;
            double y0 = CENTERY - radius + ascent;
            // 旋转角度
            double a = 2 * Math.asin(char_interval / (2 * radius2));
            if (odd) {
                g2.setFont(f);
                g2.drawString(messages[first], (float) (x0 - char_interval / 2), (float) y0);
                // 中心点的右边
                for (int i = first + 1; i < ilength; i++) {
                    double aa = (i - first) * a;
                    double ax = radius2 * Math.sin(aa);
                    double ay = radius2 - radius2 * Math.cos(aa);
                    AffineTransform transform = AffineTransform.getRotateInstance(aa);
                    Font f2 = f.deriveFont(transform);
                    g2.setFont(f2);
                    g2.drawString(messages[i], (float) (x0 + ax - char_interval / 2 * Math.cos(aa)), (float) (y0 + ay - char_interval / 2 * Math.sin(aa)));
                }
                // 中心点的左边
                for (int i = first - 1; i > -1; i--) {
                    double aa = (first - i) * a;
                    double ax = radius2 * Math.sin(aa);
                    double ay = radius2 - radius2 * Math.cos(aa);
                    AffineTransform transform = AffineTransform.getRotateInstance(-aa);// ,x0 + ax, y0 + ay);
                    Font f2 = f.deriveFont(transform);
                    g2.setFont(f2);
                    g2.drawString(messages[i], (float) (x0 - ax - char_interval / 2 * Math.cos(aa)), (float) (y0 + ay + char_interval / 2 * Math.sin(aa)));
                }
            } else {
                // 中心点的右边
                for (int i = second; i < ilength; i++) {
                    double aa = (i - second + 0.5) * a;
                    double ax = radius2 * Math.sin(aa);
                    double ay = radius2 - radius2 * Math.cos(aa);
                    AffineTransform transform = AffineTransform.getRotateInstance(aa);// ,x0 + ax, y0 + ay);
                    Font f2 = f.deriveFont(transform);
                    g2.setFont(f2);
                    g2.drawString(messages[i], (float) (x0 + ax - char_interval / 2 * Math.cos(aa)), (float) (y0 + ay - char_interval / 2 * Math.sin(aa)));
                }
                // 中心点的左边
                for (int i = first; i > -1; i--) {
                    double aa = (first - i + 0.5) * a;
                    double ax = radius2 * Math.sin(aa);
                    double ay = radius2 - radius2 * Math.cos(aa);
                    AffineTransform transform = AffineTransform.getRotateInstance(-aa);// ,x0 + ax, y0 + ay);
                    Font f2 = f.deriveFont(transform);
                    g2.setFont(f2);
                    g2.drawString(messages[i], (float) (x0 - ax - char_interval / 2 * Math.cos(aa)), (float) (y0 + ay + char_interval / 2 * Math.sin(aa)));
                }
            }
            g2.dispose();
            ImageIO.write(buffImg, "png", byteArrayOutputStream);
        } catch (IOException e) {
            // LOGGER.error("", e);
        }
        return ImageUtil.resizeImage(byteArrayOutputStream.toByteArray(), width, height);
    }
}