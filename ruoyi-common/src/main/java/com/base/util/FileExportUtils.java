package com.base.util;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class FileExportUtils {

    private static boolean isinit = false;
    private static Set<String> imageSuffix = new HashSet();

    static {
        imageSuffix.add("BMP");
        imageSuffix.add("DUB");
        imageSuffix.add("GIF");
        imageSuffix.add("JFIF");
        imageSuffix.add("JPE");
        imageSuffix.add("JPEG");
        imageSuffix.add("JPG");
        imageSuffix.add("PNG");
        imageSuffix.add("TIF");
        imageSuffix.add("TIFF");
        imageSuffix.add("ICO");
    }


    public static String getMine(byte[] bytes) throws Exception {
        return FileTypeUtil.getType(bytes);
    }

    public static String getMineType(String file) {
        Path path = Paths.get(file);
        String contentType = null;

        try {
            contentType = Files.probeContentType(path);
        } catch (IOException var4) {
//            LogKit.error((String)null, var4);
        }

        return contentType;
    }


    public static String filterPath(String path) {
        if (!StringUtil.isBlank(path) && !path.endsWith(File.separator)) {
            if (path.endsWith("\\") || path.endsWith("/")) {
                path = path.substring(0, path.length() - 1);
            }

            path = path + File.separator;
        }

        return path;
    }

    public static String getSuffix(String fileName) {
        return String.valueOf(fileName.substring(fileName.lastIndexOf(".") + 1)).toUpperCase();
    }

    public static byte[] is2byte(InputStream in) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[10240];
        boolean var3 = true;

        int count;
        while((count = in.read(data, 0, 10240)) != -1) {
            outStream.write(data, 0, count);
        }

        data = (byte[])null;
        return outStream.toByteArray();
    }

    public static boolean isImage(String fileName) {
        boolean isImage = false;
        if (!StringUtil.isBlank(fileName)) {
            String fileSuffix = getSuffix(fileName);
            isImage = imageSuffix.contains(fileSuffix);
        }

        return isImage;
    }

    public static boolean isVideo(String mime) {
        boolean isImage = false;
        if (!StringUtil.isBlank(mime)) {
            isImage = mime.indexOf("video") != -1;
        }

        return isImage;
    }


    public static void download(HttpServletRequest request, HttpServletResponse response, String filename, String mimetype, File file, String type) throws Exception {
        if (!file.exists()) {
            throw new Exception("文件没找到");
        } else {
            OutputStream os = null;
            OutputStream out = null;
            long fileLength = file.length();
            long pastLength = 0L;
            int rangeSwitch = 0;
            long toLength = 0L;
            long contentLength = 0L;
            String rangeBytes = "";
            RandomAccessFile raf = null;
            byte[] b = new byte[1024];
            response.setHeader("Accept-Ranges", "0-" + fileLength);
            String contentRange;
            if (request.getHeader("Range") != null) {
                response.setStatus(206);
                rangeBytes = request.getHeader("Range").replaceAll("bytes=", "");
                if (rangeBytes.indexOf(45) == rangeBytes.length() - 1) {
                    rangeSwitch = 1;
                    rangeBytes = rangeBytes.substring(0, rangeBytes.indexOf(45));
                    pastLength = Long.parseLong(rangeBytes.trim());
                    contentLength = fileLength - pastLength;
                } else {
                    rangeSwitch = 2;
                    contentRange = rangeBytes.substring(0, rangeBytes.indexOf(45));
                    String temp2 = rangeBytes.substring(rangeBytes.indexOf(45) + 1, rangeBytes.length());
                    pastLength = Long.parseLong(contentRange.trim());
                    toLength = Long.parseLong(temp2);
                    contentLength = toLength - pastLength;
                }
            } else {
                contentLength = fileLength;
            }

            if (pastLength != 0L) {
                switch(rangeSwitch) {
                    case 1:
                        contentRange = "bytes " + (new Long(pastLength)).toString() + "-" + (new Long(fileLength - 1L)).toString() + "/" + (new Long(fileLength)).toString();
                        response.setHeader("Content-Range", contentRange);
                        break;
                    case 2:
                        contentRange = rangeBytes + "/" + (new Long(fileLength)).toString();
                        response.setHeader("Content-Range", contentRange);
                }
            }

            try {
                response.setHeader("cache", "state");
                response.setHeader("Cache-Control", "max-age=2592000");
                response.setContentType(mimetype + "; charset=utf-8");
                if ("inline".equals(type)) {
                    response.setHeader("Content-Disposition", "inline; filename=" + URLEncoder.encode(filename, "UTF-8"));
                } else {
                    response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
                }

                response.addHeader("Content-Length", String.valueOf(contentLength));
                os = response.getOutputStream();
                out = new BufferedOutputStream(os);
                raf = new RandomAccessFile(file, "r");

                try {

                    int n;
                    label192:
                    switch(rangeSwitch) {
                        case 0:
                        case 1:
                            raf.seek(pastLength);
                            n = 0;

                            while(true) {
                                if ((n = raf.read(b, 0, 1024)) == -1) {
                                    break label192;
                                }

                                out.write(b, 0, n);
                            }
                        case 2:
                            raf.seek(pastLength);
                            n = 0;
                            long readLength = 0L;

                            while(readLength <= contentLength - 1024L) {
                                n = raf.read(b, 0, 1024);
                                readLength += 1024L;
                                out.write(b, 0, n);
                            }

                            if (readLength <= contentLength) {
                                n = raf.read(b, 0, (int)(contentLength - readLength));
                                out.write(b, 0, n);
                            }
                    }
                } catch (Exception var36) {
                    ;
                }

                os.flush();
            } catch (Exception var37) {
                var37.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException var35) {
                        System.out.println(var35.getMessage());
                    }
                }

                if (raf != null) {
                    try {
                        raf.close();
                    } catch (IOException var34) {
                        System.out.println(var34.getMessage());
                    }
                }

            }

        }
    }



}
