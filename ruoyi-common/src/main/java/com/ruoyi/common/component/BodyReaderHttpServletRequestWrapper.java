package com.ruoyi.common.component;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper{
    private final byte[] body;

    private Map<String, String[]> params;

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        String sessionStream = getBodyString(request);
        body = sessionStream.getBytes(Charset.forName("UTF-8"));
        this.params = new HashMap();
        this.params.putAll(request.getParameterMap());
    }

    /**
     * 获取请求Body
     *
     * @param request
     * @return
     */
    public String getBodyString(final ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = cloneInputStream(request.getInputStream());//将获取到的请求参数重新塞入request里面去
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * Description: 复制输入流</br>
     *
     * @param inputStream
     * @return</br>
     */
    public InputStream cloneInputStream(ServletInputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buffer)) > -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return byteArrayInputStream;
    }
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }
        };
    }


    public String getParameter(String name) {
        String[] values = (String[])this.params.get(name);
        return values != null && values.length != 0 ? values[0] : null;
    }

    public Map<String, String[]> getParameterMap() {
        return this.params;
    }

    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(this.params.keySet());
    }

    public Map<String, String[]> getParams() {
        return this.params;
    }

    public String[] getParameterValues(String name) {
        return (String[])this.params.get(name);
    }

    public void addAllParameters(Map<String, Object> otherParams) {
        Iterator var2 = otherParams.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry)var2.next();
            this.addParameter((String)entry.getKey(), entry.getValue());
        }

    }

    public void addParameter(String name, Object value) {
        if (value != null) {
            if (value instanceof String[]) {
                this.params.put(name, (String[])((String[])value));
            } else if (value instanceof String) {
                this.params.put(name, new String[]{(String)value});
            } else {
                this.params.put(name, new String[]{String.valueOf(value)});
            }
        }

    }
}
