package com.paidang.action;

import com.alibaba.fastjson.JSONObject;
import com.base.api.ApiException;
import com.base.util.CoreConstants;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping(path = "/apk")
public class ApkController {
    @PostMapping("/upload")
    public JSONObject upload(HttpServletRequest request) throws IOException, URISyntaxException, FileUploadException {
        if (!ServletFileUpload.isMultipartContent(request)){
            throw new ApiException("file");
        }

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");

        List<FileItem> list = upload.parseRequest(request);
        int max = list == null ? -1 : list.size() - 1;
        if (max == -1){
            throw new ApiException("file");
        }
        FileItem fileItem = null;
        for (int i = 0; i < list.size(); i++) {
            FileItem file = list.get(i);
            if (!file.isFormField()){
                fileItem=file;
            }
        }
        URI url = new URI(CoreConstants.getProperty("apkUploadUrl"));
        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        builder.addBinaryBody("file",fileItem.get());
//        builder.setContentType(ContentType.MULTIPART_FORM_DATA);
//        builder.setCharset(Charset.forName("UTF-8"));
        builder.addBinaryBody("file",fileItem.get(),ContentType.MULTIPART_FORM_DATA,new String(fileItem.getName().getBytes(), StandardCharsets.UTF_8));
        // 上传的文件
        HttpEntity httpEntity = builder.build();
        httpPost.setEntity(httpEntity);
        // 请求获取数据的超时时间 、 设置从connect
        // Manager获取Connection超时时间（因为目前版本是可以共享连接池的）、设置连接超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(10000).setConnectionRequestTimeout(3000)
                .setConnectTimeout(10000).build();
        httpPost.setConfig(requestConfig);
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error",0);
        jsonObject.put("message","文件上传成功");
        jsonObject.put("url",result);
        return jsonObject;
    }
}
