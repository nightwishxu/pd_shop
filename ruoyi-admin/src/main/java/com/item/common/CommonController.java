//package com.item.common;
//
//
//import com.base.util.CoreConstants;
//import com.paidang.service.BFileService;
//import com.ruoyi.common.core.domain.ApiFile;
//import com.ruoyi.common.core.domain.Ret;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileItemFactory;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
//@Controller
//@Api(tags = "CommonController", description = "上传文件")
//@RequestMapping("/common")
//public class CommonController {
//
//    @Autowired
//    private BFileService fileService;
//
//    @ApiOperation(value = "文件上传", notes = "")
//    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
//    @ResponseBody
//    public Ret upload(HttpServletRequest request) throws Exception {
//        if (!ServletFileUpload.isMultipartContent(request)){
////            throw new ApiException("files");
//            return Ret.error();
//        }
//
//        FileItemFactory factory = new DiskFileItemFactory();
//        ServletFileUpload upload = new ServletFileUpload(factory);
//        upload.setHeaderEncoding("UTF-8");
//
//        List<FileItem> list = upload.parseRequest(request);
//
//        ApiFile ret = new ApiFile();
//        if (CollectionUtils.isEmpty(list)){
//            return Ret.error();
//        }
//
//        StringBuffer sb = new StringBuffer();
//        StringBuffer url = new StringBuffer();
//        for (int i = 0;i<list.size() ; i++){
//            FileItem file = list.get(i);
//            if (!file.isFormField()){
//                if (sb.length() > 0){
//                    sb.append(",");
//                    url.append(",");
//                }
//                String temp = fileService.uploadFile(file.get(), file.getName(), BFileService.LOCAL);
//                sb.append(temp);
//                url.append(getUrl(temp));
//            }
//            if (i == (list.size()-1)){
//                ret.setId(sb.toString());
//                ret.setPath(url.toString());
//                return Ret.success(ret);
//            }
//        }
//        return null;
//
//    }
//
//
//    protected String getUrl(String fileid) {
//        return this.getUrl(fileid, (Integer)null, (Integer)null);
//    }
//
//    protected String getUrl(String fileid, Integer width, Integer height) {
//        if (StringUtils.isNotBlank(fileid)) {
//            String url = CoreConstants.SERVER_URL + "download?id=" + fileid;
//            if (width != null) {
//                url = url + "&w=" + width;
//            }
//
//            if (height != null) {
//                url = url + "&h=" + height;
//            }
//
//            return url;
//        } else {
//            return "";
//        }
//    }
//
//
//    @RequestMapping("/download")
//    public void downloadFile(String id,HttpServletResponse resp,
//                             @RequestParam(value = "w", required = false) String w,
//                             @RequestParam(value = "h", required = false) String h,
//                             @RequestParam(value = "q", required = false) String q
//            ,String type,HttpServletRequest request) throws Exception{
//        fileService.getFile(id, w, h, q, resp, type,request);
//    }
//
//
//}
