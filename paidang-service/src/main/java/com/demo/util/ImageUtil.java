package com.demo.util;

import javax.imageio.*;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

public class ImageUtil {
    public static final String PNG = "png";
    private static final double INCH_2_CM = 2.54d;

    public static void converterImage(File imgFile, String format, File formatFile) throws IOException {
        BufferedImage bIMG = ImageIO.read(imgFile);
        ImageIO.write(bIMG, format, formatFile);
    }

    public static void createSealImage(String drawStr, int width, int height, Integer fontHeight, String sealImagePath) throws Exception {
        if (fontHeight == null) {
            fontHeight = 10;
        }
        try {
            BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D gd = buffImg.createGraphics();
            // 设置透明 start
            buffImg = gd.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
            gd = buffImg.createGraphics();
            // 设置透明 end
            FontRenderContext context = gd.getFontRenderContext();
            Font font = new Font("微软雅黑", Font.PLAIN, fontHeight);
            Rectangle2D bounds = font.getStringBounds(drawStr, context);
            double y = (height - bounds.getHeight()) / 2;
            double ascent = -bounds.getY();
            double baseY = y + ascent;
            gd.setFont(font); // 设置字体
            gd.setColor(Color.RED); // 设置颜色
            gd.drawRect(0, 0, width - 1, height - 1); // 画边框
            gd.drawString(drawStr, width / 2 - fontHeight * drawStr.length() / 2, (int) baseY); // 输出文字（中文横向居中）
            ImageIO.write(buffImg, PNG, new File(sealImagePath));
        } catch (Exception e) {
            throw e;
        }
    }

    public static byte[] resizeImage(byte[] imageByte, int width, int height) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageByte);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            Image srcImg = ImageIO.read(bais);
            if (srcImg.getWidth(null) == width && srcImg.getHeight(null) == height) {
                return imageByte;
            }

            BufferedImage buffImg = null;
            buffImg = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            buffImg.getGraphics().drawImage(srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            ImageIO.write(buffImg, PNG, baos);
            return baos.toByteArray();
        } catch (Exception e) {
            throw e;
        } finally {
            if (bais != null) {
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getImageSuffix(InputStream imageFile) {
        try {
            Iterator<ImageReader> iter = ImageIO.getImageReaders(ImageIO.createImageInputStream(imageFile));
            if (!iter.hasNext()) {
                return null;
            }
            return iter.next().getFormatName().toLowerCase();
        } catch (IOException e) {
            // LOGGER.error("ImageUtil getImageSuffix error", e);
            return null;
        }
    }

    public static byte[] setImageDPI(BufferedImage image, int dpi) throws Exception {
        for (Iterator<ImageWriter> iw = ImageIO.getImageWritersByFormatName(PNG); iw.hasNext();) {
            ImageWriter writer = iw.next();
            ImageWriteParam writeParam = writer.getDefaultWriteParam();
            ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_RGB);
            IIOMetadata metadata = writer.getDefaultImageMetadata(typeSpecifier, writeParam);
            if (metadata.isReadOnly() || !metadata.isStandardMetadataFormatSupported()) {
                continue;
            }
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageOutputStream stream = null;
            try {
                setDPI(metadata, dpi);
                stream = ImageIO.createImageOutputStream(output);
                writer.setOutput(stream);
                writer.write(metadata, new IIOImage(image, null, metadata), writeParam);
            } catch (Exception e) {
                throw e;
            } finally {
                try {
                    if (stream != null) {
                        stream.close();
                    }
                } catch (IOException e) {
                }
            }
            return output.toByteArray();
        }
        return null;
    }

    private static void setDPI(IIOMetadata metadata, int dpi) throws IIOInvalidTreeException {
        double dotsPerMilli = 1.0 * dpi / 10 * INCH_2_CM;
        IIOMetadataNode horiz = new IIOMetadataNode("HorizontalPixelSize");
        horiz.setAttribute("value", Double.toString(dotsPerMilli));

        IIOMetadataNode vert = new IIOMetadataNode("VerticalPixelSize");
        vert.setAttribute("value", Double.toString(dotsPerMilli));

        IIOMetadataNode dim = new IIOMetadataNode("Dimension");
        dim.appendChild(horiz);
        dim.appendChild(vert);

        IIOMetadataNode root = new IIOMetadataNode("javax_imageio_1.0");
        root.appendChild(dim);

        metadata.mergeTree("javax_imageio_1.0", root);
    }
}
