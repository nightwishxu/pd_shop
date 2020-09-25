package com.base.util;


import cn.hutool.core.io.IORuntimeException;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.util.Collection;

public final class IoUtil {
    public static final int DEFAULT_BUFFER_SIZE = 1024;
    public static final int DEFAULT_LARGE_BUFFER_SIZE = 4096;
    public static final int EOF = -1;

    private IoUtil() {
    }

    public static long copy(Reader reader, Writer writer) throws IOException {
        return copy((Reader)reader, (Writer)writer, 1024);
    }

    public static long copy(Reader reader, Writer writer, int bufferSize) throws IOException {
        return copy((Reader)reader, (Writer)writer, bufferSize, (StreamProgress)null);
    }

    public static long copy(Reader reader, Writer writer, int bufferSize, StreamProgress streamProgress) throws IOException {
        char[] buffer = new char[bufferSize];
        long size = 0L;
        if (null != streamProgress) {
            streamProgress.start();
        }

        int readSize;
        while((readSize = reader.read(buffer, 0, bufferSize)) != -1) {
            writer.write(buffer, 0, readSize);
            size += (long)readSize;
            writer.flush();
            if (null != streamProgress) {
                streamProgress.progress(size);
            }
        }

        if (null != streamProgress) {
            streamProgress.finish();
        }

        return size;
    }

    public static long copy(InputStream in, OutputStream out) throws IOException {
        return copy((InputStream)in, (OutputStream)out, 1024);
    }

    public static long copy(InputStream in, OutputStream out, int bufferSize) throws IOException {
        return copy((InputStream)in, (OutputStream)out, bufferSize, (StreamProgress)null);
    }

    public static long copy(InputStream in, OutputStream out, int bufferSize, StreamProgress streamProgress) throws IOException {
        if (null == in) {
            throw new NullPointerException("InputStream is null!");
        } else if (null == out) {
            throw new NullPointerException("OutputStream is null!");
        } else {
            if (bufferSize <= 0) {
                bufferSize = 1024;
            }

            byte[] buffer = new byte[bufferSize];
            long size = 0L;
            if (null != streamProgress) {
                streamProgress.start();
            }

            boolean var7 = true;

            int readSize;
            while((readSize = in.read(buffer)) != -1) {
                out.write(buffer, 0, readSize);
                size += (long)readSize;
                out.flush();
                if (null != streamProgress) {
                    streamProgress.progress(size);
                }
            }

            if (null != streamProgress) {
                streamProgress.finish();
            }

            return size;
        }
    }

    public static long copyByNIO(InputStream in, OutputStream out, int bufferSize, StreamProgress streamProgress) throws IOException {
        return copy(Channels.newChannel(in), Channels.newChannel(out), bufferSize, streamProgress);
    }

    public static long copy(FileInputStream in, FileOutputStream out) throws IOException {
        if (null == in) {
            throw new NullPointerException("FileInputStream is null!");
        } else if (null == out) {
            throw new NullPointerException("FileOutputStream is null!");
        } else {
            FileChannel inChannel = in.getChannel();
            FileChannel outChannel = out.getChannel();
            return inChannel.transferTo(0L, inChannel.size(), outChannel);
        }
    }

    public static long copy(ReadableByteChannel in, WritableByteChannel out, int bufferSize, StreamProgress streamProgress) throws IOException {
        if (null == in) {
            throw new NullPointerException("In is null!");
        } else if (null == out) {
            throw new NullPointerException("Out is null!");
        } else {
            ByteBuffer byteBuffer = ByteBuffer.allocate(bufferSize);
            long size = 0L;
            if (null != streamProgress) {
                streamProgress.start();
            }

            while(in.read(byteBuffer) != -1) {
                byteBuffer.flip();
                size += (long)out.write(byteBuffer);
                byteBuffer.clear();
                if (null != streamProgress) {
                    streamProgress.progress(size);
                }
            }

            if (null != streamProgress) {
                streamProgress.finish();
            }

            return size;
        }
    }

    public static BufferedReader getReader(InputStream in, String charsetName) {
        return getReader(in, Charset.forName(charsetName));
    }

    public static BufferedReader getReader(InputStream in, Charset charset) {
        if (null == in) {
            return null;
        } else {
            InputStreamReader reader = null;
            if (null == charset) {
                reader = new InputStreamReader(in);
            } else {
                reader = new InputStreamReader(in, charset);
            }

            return new BufferedReader(reader);
        }
    }

    public static OutputStreamWriter getWriter(OutputStream out, String charsetName) {
        return getWriter(out, Charset.forName(charsetName));
    }

    public static OutputStreamWriter getWriter(OutputStream out, Charset charset) {
        if (null == out) {
            return null;
        } else {
            return null == charset ? new OutputStreamWriter(out) : new OutputStreamWriter(out, charset);
        }
    }

    public static String read(InputStream in, String charsetName) throws IOException {
        FastByteArrayOutputStream out = read(in);
        return StringUtil.isBlank(charsetName) ? out.toString() : out.toString(charsetName);
    }

    public static String read(InputStream in, Charset charset) throws IOException {
        FastByteArrayOutputStream out = read(in);
        return null == charset ? out.toString() : out.toString(charset);
    }

    public static FastByteArrayOutputStream read(InputStream in) throws IOException {
        FastByteArrayOutputStream out = new FastByteArrayOutputStream();
        copy((InputStream)in, (OutputStream)out);
        return out;
    }

    public static String read(Reader reader) throws IOException {
        StringBuilder builder = StringUtil.builder();
        CharBuffer buffer = CharBuffer.allocate(1024);

        while(-1 != reader.read(buffer)) {
            builder.append(buffer.flip().toString());
        }

        return builder.toString();
    }

    public static String read(FileChannel fileChannel, String charsetName) throws IOException {
        return read(fileChannel, CharsetUtil.charset(charsetName));
    }

    public static String read(FileChannel fileChannel, Charset charset) throws IOException {
        MappedByteBuffer buffer = fileChannel.map(MapMode.READ_ONLY, 0L, fileChannel.size()).load();
        return StringUtil.str(buffer, charset);
    }

    public static byte[] readBytes(InputStream in) throws IOException {
        FastByteArrayOutputStream out = new FastByteArrayOutputStream();
        copy((InputStream)in, (OutputStream)out);
        return out.toByteArray();
    }

    public static byte[] readBytes(InputStream in, int length) throws IOException {
        byte[] b = new byte[length];
        in.read(b);
        return b;
    }

    public static String readHex(InputStream in, int length, boolean toLowerCase) throws IOException {
        return HexUtil.encodeHexStr(readBytes(in, length), toLowerCase);
    }

    public static String readHex28Upper(InputStream in) throws IOException {
        return readHex(in, 28, false);
    }

    public static String readHex28Lower(InputStream in) throws IOException {
        return readHex(in, 28, false);
    }

    public static <T> T readObj(InputStream in) throws IOException {
        if (in == null) {
            throw new IllegalArgumentException("The InputStream must not be null");
        } else {
            ObjectInputStream ois = null;

            try {
                ois = new ObjectInputStream(in);
                T obj = (T)ois.readObject();
                return obj;
            } catch (Exception var3) {
                throw new IOException(var3);
            }
        }
    }

    public static <T extends Collection<String>> T readLines(InputStream in, String charsetName, T collection) throws IOException {
        return readLines(in, CharsetUtil.charset(charsetName), collection);
    }

    public static <T extends Collection<String>> T readLines(InputStream in, Charset charset, T collection) throws IOException {
        BufferedReader reader = getReader(in, charset);
        String line = null;

        while((line = reader.readLine()) != null) {
            collection.add(line);
        }

        return collection;
    }

    public static ByteArrayInputStream toStream(String content, String charsetName) {
        return toStream(content, CharsetUtil.charset(charsetName));
    }

    public static ByteArrayInputStream toStream(String content, Charset charset) {
        return content == null ? null : new ByteArrayInputStream(StringUtil.bytes(content, charset));
    }

    public static FileInputStream toStream(File file) {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException var2) {
            throw new IORuntimeException(var2);
        }
    }

    public static void write(OutputStream out, boolean isCloseOut, byte[] content) throws IOException {
        try {
            out.write(content);
        } finally {
            if (isCloseOut) {
                close((Closeable)out);
            }

        }

    }

    public static void write(OutputStream out, String charset, boolean isCloseOut, Object... contents) throws IOException {
        OutputStreamWriter osw = null;

        try {
            osw = getWriter(out, charset);
            Object[] var5 = contents;
            int var6 = contents.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Object content = var5[var7];
                if (content != null) {
                    osw.write(Convert.toStr(content, ""));
                    osw.flush();
                }
            }
        } catch (Exception var12) {
            throw new IOException("Write content to OutputStream error!", var12);
        } finally {
            if (isCloseOut) {
                close((Closeable)osw);
            }

        }

    }

    public static void write(OutputStream out, Charset charset, boolean isCloseOut, Object... contents) throws IOException {
        OutputStreamWriter osw = null;

        try {
            osw = getWriter(out, charset);
            Object[] var5 = contents;
            int var6 = contents.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Object content = var5[var7];
                if (content != null) {
                    osw.write(Convert.toStr(content, ""));
                    osw.flush();
                }
            }
        } catch (Exception var12) {
            throw new IOException("Write content to OutputStream error!", var12);
        } finally {
            if (isCloseOut) {
                close((Closeable)osw);
            }

        }

    }

    public static void writeObjects(OutputStream out, Charset charset, boolean isCloseOut, Serializable... contents) throws IOException {
        ObjectOutputStream osw = null;

        try {
            osw = out instanceof ObjectOutputStream ? (ObjectOutputStream)out : new ObjectOutputStream(out);
            Serializable[] var5 = contents;
            int var6 = contents.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Object content = var5[var7];
                if (content != null) {
                    osw.writeObject(content);
                    osw.flush();
                }
            }
        } catch (Exception var12) {
            throw new IOException("Write content to OutputStream error!", var12);
        } finally {
            if (isCloseOut) {
                close((Closeable)osw);
            }

        }

    }

    public static void writeObjects(OutputStream out, String charset, boolean isCloseOut, Serializable... contents) throws IOException {
        ObjectOutputStream osw = null;

        try {
            osw = out instanceof ObjectOutputStream ? (ObjectOutputStream)out : new ObjectOutputStream(out);
            Serializable[] var5 = contents;
            int var6 = contents.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Object content = var5[var7];
                if (content != null) {
                    osw.writeObject(content);
                    osw.flush();
                }
            }
        } catch (Exception var12) {
            throw new IOException("Write content to OutputStream error!", var12);
        } finally {
            if (isCloseOut) {
                close((Closeable)osw);
            }

        }

    }

    public static PushbackInputStream toPushbackStream(InputStream in, int pushBackSize) {
        return in instanceof PushbackInputStream ? (PushbackInputStream)in : new PushbackInputStream(in, pushBackSize);
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception var2) {
                ;
            }

        }
    }

    public static void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception var2) {
                ;
            }

        }
    }
}
