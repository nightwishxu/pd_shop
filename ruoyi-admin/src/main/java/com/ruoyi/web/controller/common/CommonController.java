package com.ruoyi.web.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.util.CoreConstants;
import com.paidang.service.BFileService;
import com.ruoyi.common.core.domain.ApiFile;
import com.ruoyi.common.core.domain.Ret;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.config.ServerConfig;

import java.util.List;

/**
 * 通用请求处理
 * 
 * @author ruoyi
 */
@RestController
public class CommonController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;


    @Autowired
    private BFileService fileService;

    /**
     * 通用下载请求
     * 
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.isValidFilename(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = RuoYiConfig.getDownloadPath() + fileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    public Ret uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            Ret ajax = Ret.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        }
        catch (Exception e)
        {
            return Ret.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/common/download/resource")
    public void resourceDownload(String name, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // 本地资源路径
        String localPath = RuoYiConfig.getProfile();
        // 数据库资源地址
        String downloadPath = localPath + StringUtils.substringAfter(name, Constants.RESOURCE_PREFIX);
        // 下载名称
        String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));
        FileUtils.writeBytes(downloadPath, response.getOutputStream());
    }


    @ApiOperation(value = "文件上传", notes = "")
    @RequestMapping(value = "/common/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Ret upload(HttpServletRequest request) throws Exception {
        if (!ServletFileUpload.isMultipartContent(request)){
//            throw new ApiException("files");
            return Ret.error();
        }
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        List<FileItem> list = upload.parseRequest(request);

        ApiFile ret = new ApiFile();
        if (CollectionUtils.isEmpty(list)){
            return Ret.error();
        }

        StringBuffer sb = new StringBuffer();
        StringBuffer url = new StringBuffer();
        for (int i = 0;i<list.size() ; i++){
            FileItem file = list.get(i);
            if (!file.isFormField()){
                if (sb.length() > 0){
                    sb.append(",");
                    url.append(",");
                }
                String temp = fileService.uploadFile(file.get(), file.getName(), BFileService.LOCAL);
                sb.append(temp);
                url.append(getUrl(temp));
            }
            if (i == (list.size()-1)){
                ret.setId(sb.toString());
                ret.setPath(url.toString());
                return Ret.success(ret);
            }
        }
        return null;

    }


    protected String getUrl(String fileid) {
        return this.getUrl(fileid, (Integer)null, (Integer)null);
    }

    protected String getUrl(String fileid, Integer width, Integer height) {
        if (org.apache.commons.lang.StringUtils.isNotBlank(fileid)) {
//            String url = CoreConstants.SERVER_URL + "download?id=" + fileid;
            String url = CoreConstants.BOS_URL + fileid;
            if (width != null) {
                url = url + "&w=" + width;
            }

            if (height != null) {
                url = url + "&h=" + height;
            }

            return url;
        } else {
            return "";
        }
    }


    @RequestMapping("/download")
    public void downloadFile(String id,HttpServletResponse resp,
                             @RequestParam(value = "w", required = false) String w,
                             @RequestParam(value = "h", required = false) String h,
                             @RequestParam(value = "q", required = false) String q
            ,String type,HttpServletRequest request) throws Exception{
        fileService.getFile(id, w, h, q, resp, type,request);
    }
}
