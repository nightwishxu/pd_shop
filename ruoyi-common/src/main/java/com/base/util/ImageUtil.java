package com.base.util;

import cn.hutool.core.io.IORuntimeException;
import com.base.expection.UtilException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.io.*;
import java.util.Iterator;

public class ImageUtil {
    public static final String IMAGE_TYPE_GIF = "gif";
    public static final String IMAGE_TYPE_JPG = "jpg";
    public static final String IMAGE_TYPE_JPEG = "jpeg";
    public static final String IMAGE_TYPE_BMP = "bmp";
    public static final String IMAGE_TYPE_PNG = "png";
    public static final String IMAGE_TYPE_PSD = "psd";

    private ImageUtil() {
    }

    public static void scale(File srcImageFile, File destImageFile, float scale) {
        try {
            scale((Image)ImageIO.read(srcImageFile), (ImageOutputStream)ImageIO.createImageOutputStream(destImageFile), scale);
        } catch (IOException var4) {
            throw new UtilException(var4);
        }
    }

    public static void scale(InputStream srcStream, OutputStream destStream, float scale) {
        try {
            scale((Image)ImageIO.read(srcStream), (ImageOutputStream)ImageIO.createImageOutputStream(destStream), scale);
        } catch (IOException var4) {
            throw new UtilException(var4);
        }
    }

    public static void scale(ImageInputStream srcStream, ImageOutputStream destStream, float scale) {
        try {
            scale((Image)ImageIO.read(srcStream), (ImageOutputStream)destStream, scale);
        } catch (IOException var4) {
            throw new UtilException(var4);
        }
    }

    public static void scale(Image srcImg, ImageOutputStream destImageStream, float scale) {
        Image image = scale(srcImg, (double)scale);
        writeJpg(image, destImageStream);
    }

    public static Image scale(Image srcImg, double scale) {
        if (scale < 0.0D) {
            scale = -scale;
        }

        int width = NumberUtil.mul(Integer.toString(srcImg.getWidth((ImageObserver)null)), Double.toString(scale)).intValue();
        int height = NumberUtil.mul(Integer.toString(srcImg.getHeight((ImageObserver)null)), Double.toString(scale)).intValue();
        return scale(srcImg, width, height);
    }

    public static Image scale(Image srcImg, int width, int height) {
        int srcHeight = srcImg.getHeight((ImageObserver)null);
        int srcWidth = srcImg.getWidth((ImageObserver)null);
        if (srcHeight == height && srcWidth == width) {
            return srcImg;
        } else {
            byte scaleType;
            if (srcHeight >= height && srcWidth >= width) {
                scaleType = 1;
            } else {
                scaleType = 4;
            }

            return srcImg.getScaledInstance(width, height, scaleType);
        }
    }

    public static final void scale(File srcImageFile, File destImageFile, int width, int height, Color fixedColor) {
        try {
            BufferedImage bufferedImage = ImageIO.read(srcImageFile);
            if (width == 0) {
                width = (int)((double)bufferedImage.getWidth() / ((double)bufferedImage.getHeight() * 1.0D) * (double)height);
            } else if (height == 0) {
                height = (int)((double)bufferedImage.getHeight() / ((double)bufferedImage.getWidth() * 1.0D) * (double)width);
            }

            scale((Image)bufferedImage, (ImageOutputStream)ImageIO.createImageOutputStream(destImageFile), width, height, fixedColor);
        } catch (IOException var6) {
            throw new UtilException(var6);
        }
    }

    public static final void scale(InputStream srcStream, OutputStream destStream, int width, int height, Color fixedColor) {
        try {
            scale((Image)ImageIO.read(srcStream), (ImageOutputStream)ImageIO.createImageOutputStream(destStream), width, height, fixedColor);
        } catch (IOException var6) {
            throw new UtilException(var6);
        }
    }

    public static final void scale(ImageInputStream srcStream, ImageOutputStream destStream, int width, int height, Color fixedColor) {
        try {
            scale((Image)ImageIO.read(srcStream), (ImageOutputStream)destStream, width, height, fixedColor);
        } catch (IOException var6) {
            throw new UtilException(var6);
        }
    }

    public static final void scale(Image srcImage, ImageOutputStream destImageStream, int width, int height, Color fixedColor) {
        Image image = scale(srcImage, width, height, fixedColor);
        writeJpg(image, destImageStream);
    }

    public static final Image scale(Image srcImage, int width, int height, Color fixedColor) {
        int srcHeight = srcImage.getHeight((ImageObserver)null);
        int srcWidth = srcImage.getWidth((ImageObserver)null);
        double heightRatio = NumberUtil.div((double)height, (double)srcHeight);
        double widthRatio = NumberUtil.div((double)width, (double)srcWidth);
        if (heightRatio == widthRatio) {
            return scale(srcImage, width, height);
        } else {
            Image itemp = null;
            if (widthRatio < (double)height) {
                itemp = scale(srcImage, width, (int)((double)srcHeight * widthRatio));
            } else {
                itemp = scale(srcImage, (int)((double)srcWidth * heightRatio), height);
            }

            if (null == fixedColor) {
                fixedColor = Color.WHITE;
            }

            BufferedImage image = new BufferedImage(width, height, 1);
            Graphics2D g = image.createGraphics();
            g.setBackground(fixedColor);
            g.clearRect(0, 0, width, height);
            int itempHeight = itemp.getHeight((ImageObserver)null);
            int itempWidth = itemp.getWidth((ImageObserver)null);
            g.drawImage(itemp, (width - itempWidth) / 2, (height - itempHeight) / 2, itempWidth, itempHeight, fixedColor, (ImageObserver)null);
            g.dispose();
            return image;
        }
    }

    public static final void cut(File srcImgFile, File destImgFile, int startX, int startY, int width, int height) {
        Rectangle rectangle = new Rectangle(startX, startY, width, height);
        cut(srcImgFile, destImgFile, rectangle);
    }

    public static final void cut(File srcImgFile, ByteArrayOutputStream baos, int startX, int startY, int width, int height) {
        Rectangle rectangle = new Rectangle(startX, startY, width, height);

        try {
            cut(ImageIO.createImageInputStream(srcImgFile), ImageIO.createImageOutputStream(baos), rectangle);
        } catch (IOException var8) {
            throw new UtilException(var8);
        }
    }

    public static final void cut(File srcImgFile, File destImgFile, Rectangle rectangle) {
        try {
            cut((Image)ImageIO.read(srcImgFile), (ImageOutputStream)ImageIO.createImageOutputStream(destImgFile), rectangle);
        } catch (IOException var4) {
            throw new UtilException(var4);
        }
    }

    public static final void cut(InputStream srcStream, OutputStream destStream, Rectangle rectangle) {
        try {
            cut((Image)ImageIO.read(srcStream), (ImageOutputStream)ImageIO.createImageOutputStream(destStream), rectangle);
        } catch (IOException var4) {
            throw new UtilException(var4);
        }
    }

    public static final void cut(ImageInputStream srcStream, ImageOutputStream destStream, Rectangle rectangle) {
        try {
            cut((Image)ImageIO.read(srcStream), (ImageOutputStream)destStream, rectangle);
        } catch (IOException var4) {
            throw new UtilException(var4);
        }
    }

    public static final void cut(Image srcImage, ImageOutputStream destImageStream, Rectangle rectangle) {
        BufferedImage tag = cut(srcImage, rectangle);
        writeJpg(tag, destImageStream);
    }

    public static BufferedImage cut(Image srcImage, Rectangle rectangle) {
        ImageFilter cropFilter = new CropImageFilter(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(srcImage.getSource(), cropFilter));
        BufferedImage result = new BufferedImage(rectangle.width, rectangle.height, 1);
        draw(result, img, new Rectangle(0, 0, rectangle.width, rectangle.height));
        return result;
    }

    public static void slice(File srcImageFile, File descDir, int destWidth, int destHeight) {
        try {
            slice((Image)ImageIO.read(srcImageFile), descDir, destWidth, destHeight);
        } catch (IOException var5) {
            throw new UtilException(var5);
        }
    }

    public static final void slice(Image srcImage, File descDir, int destWidth, int destHeight) {
        if (destWidth <= 0) {
            destWidth = 200;
        }

        if (destHeight <= 0) {
            destHeight = 150;
        }

        int srcWidth = srcImage.getHeight((ImageObserver)null);
        int srcHeight = srcImage.getWidth((ImageObserver)null);

        try {
            if (srcWidth > destWidth && srcHeight > destHeight) {
                int cols = 0;
                int rows = 0;
                if (srcWidth % destWidth == 0) {
                    cols = srcWidth / destWidth;
                } else {
                    cols = (int)Math.floor((double)(srcWidth / destWidth)) + 1;
                }


                if (srcHeight % destHeight == 0) {
                    rows = srcHeight / destHeight;
                } else {
                    rows = (int)Math.floor((double)(srcHeight / destHeight)) + 1;
                }

                for(int i = 0; i < rows; ++i) {
                    for(int j = 0; j < cols; ++j) {
                        BufferedImage tag = cut(srcImage, new Rectangle(j * destWidth, i * destHeight, destWidth, destHeight));
                        ImageIO.write(tag, "jpeg", new File(descDir, "_r" + i + "_c" + j + ".jpg"));
                    }
                }
            }

        } catch (IOException var11) {
            throw new IORuntimeException(var11);
        }
    }

    public static final void sliceByRowsAndCols(File srcImageFile, File descDir, int rows, int cols) {
        try {
            sliceByRowsAndCols((Image)ImageIO.read(srcImageFile), descDir, rows, cols);
        } catch (IOException var5) {
            throw new UtilException(var5);
        }
    }

    public static final void sliceByRowsAndCols(Image srcImage, File descDir, int rows, int cols) {
        try {
            if (rows <= 0 || rows > 20) {
                rows = 2;
            }

            if (cols <= 0 || cols > 20) {
                cols = 2;
            }

            BufferedImage bi = toBufferedImage(srcImage);
            int srcWidth = bi.getHeight();
            int srcHeight = bi.getWidth();
            if (srcWidth > 0 && srcHeight > 0) {
                int destWidth;
                if (srcWidth % cols == 0) {
                    destWidth = srcWidth / cols;
                } else {
                    destWidth = (int)Math.floor((double)(srcWidth / cols)) + 1;
                }

                int destHeight;
                if (srcHeight % rows == 0) {
                    destHeight = srcHeight / rows;
                } else {
                    destHeight = (int)Math.floor((double)(srcWidth / rows)) + 1;
                }

                for(int i = 0; i < rows; ++i) {
                    for(int j = 0; j < cols; ++j) {
                        BufferedImage tag = cut(srcImage, new Rectangle(j * destWidth, i * destHeight, destWidth, destHeight));
                        ImageIO.write(tag, "jpeg", new File(descDir, "_r" + i + "_c" + j + ".jpg"));
                    }
                }
            }

        } catch (Exception var12) {
            throw new UtilException(var12);
        }
    }

    public static final void convert(File srcImageFile, String formatName, File destImageFile) {
        try {
            convert((Image)ImageIO.read(srcImageFile), formatName, (ImageOutputStream)ImageIO.createImageOutputStream(destImageFile));
        } catch (IOException var4) {
            throw new UtilException(var4);
        }
    }

    public static final void convert(InputStream srcStream, String formatName, OutputStream destStream) {
        try {
            convert((Image)ImageIO.read(srcStream), formatName, (ImageOutputStream)ImageIO.createImageOutputStream(destStream));
        } catch (IOException var4) {
            throw new UtilException(var4);
        }
    }

    public static final void convert(ImageInputStream srcStream, String formatName, ImageOutputStream destStream) {
        try {
            convert((Image)ImageIO.read(srcStream), formatName, (ImageOutputStream)destStream);
        } catch (IOException var4) {
            throw new UtilException(var4);
        }
    }

    public static final void convert(Image srcImage, String formatName, ImageOutputStream destImageStream) {
        try {
            ImageIO.write(toBufferedImage(srcImage), formatName, destImageStream);
        } catch (IOException var4) {
            throw new IORuntimeException(var4);
        }
    }

    public static final void gray(File srcImageFile, File destImageFile) {
        try {
            gray((Image)ImageIO.read(srcImageFile), (ImageOutputStream)ImageIO.createImageOutputStream(destImageFile));
        } catch (IOException var3) {
            throw new UtilException(var3);
        }
    }

    public static final void gray(InputStream srcStream, OutputStream destStream) {
        try {
            gray((Image)ImageIO.read(srcStream), (ImageOutputStream)ImageIO.createImageOutputStream(destStream));
        } catch (IOException var3) {
            throw new UtilException(var3);
        }
    }

    public static final void gray(ImageInputStream srcStream, ImageOutputStream destStream) {
        try {
            gray((Image)ImageIO.read(srcStream), (ImageOutputStream)destStream);
        } catch (IOException var3) {
            throw new UtilException(var3);
        }
    }

    public static final void gray(Image srcImage, ImageOutputStream destImageStream) {
        BufferedImage src = gray(srcImage);

        try {
            ImageIO.write(src, "jpeg", destImageStream);
        } catch (IOException var4) {
            throw new IORuntimeException(var4);
        }
    }

    public static BufferedImage gray(Image srcImage) {
        BufferedImage grayImage = toBufferedImage(srcImage);
        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(1003), (RenderingHints)null);
        grayImage = op.filter(grayImage, (BufferedImage)null);
        return grayImage;
    }

    public static final void pressText(File srcFile, File destFile, String pressText, Color color, Font font, int x, int y, float alpha) {
        try {
            pressText((Image)ImageIO.read(srcFile), (ImageOutputStream)ImageIO.createImageOutputStream(destFile), pressText, color, font, x, y, alpha);
        } catch (IOException var9) {
            throw new UtilException(var9);
        }
    }

    public static final void pressText(InputStream srcStream, OutputStream destStream, String pressText, Color color, Font font, int x, int y, float alpha) {
        try {
            pressText((Image)ImageIO.read(srcStream), (ImageOutputStream)ImageIO.createImageOutputStream(destStream), pressText, color, font, x, y, alpha);
        } catch (IOException var9) {
            throw new UtilException(var9);
        }
    }

    public static final void pressText(ImageInputStream srcStream, ImageOutputStream destStream, String pressText, Color color, Font font, int x, int y, float alpha) {
        try {
            pressText((Image)ImageIO.read(srcStream), (ImageOutputStream)destStream, pressText, color, font, x, y, alpha);
        } catch (IOException var9) {
            throw new UtilException(var9);
        }
    }

    public static final void pressText(Image srcImage, ImageOutputStream destImageStream, String pressText, Color color, Font font, int x, int y, float alpha) {
        int width = srcImage.getWidth((ImageObserver)null);
        int height = srcImage.getHeight((ImageObserver)null);
        BufferedImage image = new BufferedImage(width, height, 1);
        Graphics2D g = image.createGraphics();
        g.drawImage(srcImage, 0, 0, width, height, (ImageObserver)null);
        g.setColor(color);
        g.setFont(font);
        g.setComposite(AlphaComposite.getInstance(10, alpha));
        int fontSize = font.getSize();
        g.drawString(pressText, (width - getLength(pressText) * fontSize) / 2 + x, (height - fontSize) / 2 + y);
        g.dispose();

        try {
            ImageIO.write(image, "jpeg", destImageStream);
        } catch (IOException var14) {
            throw new IORuntimeException(var14);
        }
    }

    public static final void pressImage(File srcImageFile, File destImageFile, Image pressImg, int x, int y, float alpha) {
        try {
            pressImage((Image)ImageIO.read(srcImageFile), (ImageOutputStream)ImageIO.createImageOutputStream(destImageFile), pressImg, x, y, alpha);
        } catch (IOException var7) {
            throw new UtilException(var7);
        }
    }

    public static final void pressImage(InputStream srcStream, OutputStream destStream, Image pressImg, int x, int y, float alpha) {
        try {
            pressImage((Image)ImageIO.read(srcStream), (ImageOutputStream)ImageIO.createImageOutputStream(destStream), pressImg, x, y, alpha);
        } catch (IOException var7) {
            throw new UtilException(var7);
        }
    }

    public static final void pressImage(ImageInputStream srcStream, ImageOutputStream destStream, Image pressImg, int x, int y, float alpha) {
        try {
            pressImage((Image)ImageIO.read(srcStream), (ImageOutputStream)destStream, pressImg, x, y, alpha);
        } catch (IOException var7) {
            throw new UtilException(var7);
        }
    }

    public static final void pressImage(Image srcImage, ImageOutputStream destImageStream, Image pressImg, int x, int y, float alpha) {
        int width = srcImage.getWidth((ImageObserver)null);
        int height = srcImage.getHeight((ImageObserver)null);
        BufferedImage image = new BufferedImage(width, height, 1);
        Graphics2D g = image.createGraphics();
        g.drawImage(srcImage, 0, 0, width, height, (ImageObserver)null);
        int pressImgWidth = pressImg.getWidth((ImageObserver)null);
        int pressImgHeight = pressImg.getHeight((ImageObserver)null);
        x += (width - pressImgWidth) / 2;
        y += (height - pressImgHeight) / 2;
        g.setComposite(AlphaComposite.getInstance(10, alpha));
        g.drawImage(pressImg, x, y, pressImgWidth, pressImgHeight, (ImageObserver)null);
        g.dispose();

        try {
            ImageIO.write(image, "jpeg", destImageStream);
        } catch (IOException var13) {
            throw new IORuntimeException(var13);
        }
    }

    public static BufferedImage toBufferedImage(Image img) {
        return img instanceof BufferedImage ? (BufferedImage)img : copyImage(img, 1);
    }

    public static BufferedImage copyImage(Image img, int imageType) {
        BufferedImage bimage = new BufferedImage(img.getWidth((ImageObserver)null), img.getHeight((ImageObserver)null), imageType);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, (ImageObserver)null);
        bGr.dispose();
        return bimage;
    }

//    public static BufferedImage toImage(String base64) throws IORuntimeException {
//        byte[] decode = Base64.decode(base64, CharsetUtil.CHARSET_UTF_8);
//        return toImage(decode);
//    }

    public static BufferedImage toImage(byte[] imageBytes) throws IORuntimeException {
        try {
            return ImageIO.read(new ByteArrayInputStream(imageBytes));
        } catch (IOException var2) {
            throw new IORuntimeException(var2);
        }
    }

    public static void createImage(String str, Font font, Color backgroundColor, Color fontColor, ImageOutputStream out) throws UtilException {
        Rectangle2D r = font.getStringBounds(str, new FontRenderContext(AffineTransform.getScaleInstance(1.0D, 1.0D), false, false));
        int unitHeight = (int)Math.floor(r.getHeight());
        int width = (int)Math.round(r.getWidth()) + 1;
        int height = unitHeight + 3;
        BufferedImage image = new BufferedImage(width, height, 4);
        Graphics g = image.getGraphics();
        g.setColor(backgroundColor);
        g.fillRect(0, 0, width, height);
        g.setColor(fontColor);
        g.setFont(font);
        g.drawString(str, 0, font.getSize());
        g.dispose();
        writePng(image, out);
    }

    public static Font createFont(File fontFile) {
        try {
            return Font.createFont(0, fontFile);
        } catch (FontFormatException var4) {
            try {
                return Font.createFont(1, fontFile);
            } catch (Exception var3) {
                throw new UtilException(var4);
            }
        } catch (IOException var5) {
            throw new UtilException(var5);
        }
    }

    public static Font createFont(InputStream fontStream) {
        try {
            return Font.createFont(0, fontStream);
        } catch (FontFormatException var4) {
            try {
                return Font.createFont(1, fontStream);
            } catch (Exception var3) {
                throw new UtilException(var4);
            }
        } catch (IOException var5) {
            throw new UtilException(var5);
        }
    }

    public static void writeJpg(Image image, ImageOutputStream destImageStream) throws IORuntimeException {
        try {
            ImageIO.write(toBufferedImage(image), "jpeg", destImageStream);
        } catch (IOException var3) {
            throw new IORuntimeException(var3);
        }
    }

    public static void writePng(Image image, ImageOutputStream destImageStream) throws IORuntimeException {
        try {
            ImageIO.write(toBufferedImage(image), "png", destImageStream);
        } catch (IOException var3) {
            throw new IORuntimeException(var3);
        }
    }

//    public static void write(Image image, File targetFile) throws IORuntimeException {
//        try {
//            ImageIO.write(toBufferedImage(image), FileUtil.extName(targetFile), targetFile);
//        } catch (IOException var3) {
//            throw new IORuntimeException(var3);
//        }
//    }

    public static ImageReader getReader(String type) {
        Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(type);
        return iterator.hasNext() ? (ImageReader)iterator.next() : null;
    }

    private static BufferedImage draw(BufferedImage backgroundImg, Image img, Rectangle rectangle) {
        Graphics g = backgroundImg.getGraphics();
        g.drawImage(img, rectangle.x, rectangle.y, rectangle.width, rectangle.height, (ImageObserver)null);
        g.dispose();
        return backgroundImg;
    }

    private static final int getLength(String text) {
        int length = 0;

        for(int i = 0; i < text.length(); ++i) {
            if ((new String(text.charAt(i) + "")).getBytes().length > 1) {
                length += 2;
            } else {
                ++length;
            }
        }

        return length / 2;
    }

    public static void main(String[] args) {
        scale((File)(new File("C:\\Users\\sun\\Pictures\\下载.jpg")), (File)(new File("C:\\Users\\sun\\Pictures\\下载_10.jpg")), 10, 0, (Color)null);
    }
}
