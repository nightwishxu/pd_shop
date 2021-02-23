package com.paidang.service;


import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.PutObjectResponse;
import com.base.util.*;
import com.beust.jcommander.internal.Lists;
import com.demo.constant.HttpConnector;
import com.paidang.dao.BFileMapper;
import com.paidang.dao.model.BFile;
import com.paidang.dao.model.BFileExample;
import com.paidang.domain.pojo.FileConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.file.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class BFileService {
	@Autowired
	private BFileMapper bFileMapper;

	public static  String LOCAL = "local";
	public static  String OSS = "oss";
	public static  String COS = "cos";

	@Value("${baidu.accessKey}")
	private   String ACCESS_KEY_ID;
	@Value("${baidu.secretKey}")
	private   String SECRET_ACCESS_KEY;
	@Value("${baidu.endPoint}")
	private   String ENDPOINT ;

	@Value("${baidu.back.endPoint}")
	private   String BACK_ENDPOINT ;

	@Value("${baidu.bucketName}")
	private  String BUCKET_NAME ;

	@Value("${baidu.back.bucketName}")
	private  String BUCKET_BACK_NAME ;

	@Autowired
	private RedisCache redisCache;

	private static BosClientConfiguration config = new BosClientConfiguration();

	private static BosClient client =null;

	private static final Logger logger = LoggerFactory.getLogger(BFileService.class);

	public long countByExample(BFileExample example) {
		return this.bFileMapper.countByExample(example);
	}

	public BFile selectByPrimaryKey(String fileId) {
		return this.bFileMapper.selectByPrimaryKey(fileId);
	}

	public List<BFile> selectByExample(BFileExample example) {
		return this.bFileMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(String fileId) {
		return this.bFileMapper.deleteByPrimaryKey(fileId);
	}

	public int updateByPrimaryKeySelective(BFile record) {
		return this.bFileMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(BFile record) {
		return this.bFileMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(BFileExample example) {
		return this.bFileMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(BFile record, BFileExample example) {
		return this.bFileMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(BFile record, BFileExample example) {
		return this.bFileMapper.updateByExample(record, example);
	}

	public int insert(BFile record) {
		return this.bFileMapper.insert(record);
	}

	public int insertSelective(BFile record) {
		return this.bFileMapper.insertSelective(record);
	}

//	public String uploadHeadImg(String url) {
//		try {
//			String savePath = UUID.randomUUID().toString();
//			byte[] bytes = URLUtil.download(url);
//
//			String type = FileTypeUtil.getType(bytes);
//			if (type != null)
//				savePath += "." + type.toLowerCase();
//			return uploadFile(bytes, savePath, null);
//		} catch (Exception e) {
//			return null;
//		}
//	}

//	public String getVideoImg(String id, String cut) throws Exception {
//		if (StringUtil.isBlank(id))
//			return null;
//		BFile item = bFileMapper.selectByPrimaryKey(id);
//		if (item == null) {
//			logger.error("文件不存在;id=" + id);
//			return null;
//		} else {
//			if (StringUtil.isNotBlank(cut)) {
//				if (!cut.equals(item.getFileCreater())) {
//					item.setFileCreater(cut);
//					bFileMapper.updateByPrimaryKey(item);
//				}
//				return cut;
//			} else {
//				// 生成
//				String cutImg = UUID.randomUUID().toString().replace("-", "");
//				Calendar cal = Calendar.getInstance();
//				String realdir = cal.get(Calendar.YEAR) + "-"
//						+ (cal.get(Calendar.MONTH) + 1) + "-"
//						+ (cal.get(Calendar.DAY_OF_MONTH)) + "/"
//						+ "png";
//				File f = new File(CoreConstants.FILE_PATH, realdir);
//				if (!f.isDirectory()) {
//					f.mkdirs();
//				}
//				File filepath = new File(CoreConstants.FILE_PATH + "/"
//						+ item.getFilePath());
//				File uploadedImgFile = new File(f.getPath(), cutImg + ".png");
//				if (filepath.exists() && uploadedImgFile.exists()) {
//					BFile image = new BFile();
//					image.setFileId(cutImg);
//					image.setFileMinitype("image/png");
//					image.setFilePath(realdir + "/" + cutImg + ".png");
//					image.setFileState(1);
//					image.setFileCreatetime(new Date());
//					image.setFileName("videocut.png");
//					image.setFileCreater("320x240");
//					image.setFileBelong(BFileService.LOCAL);
//					int i = bFileMapper.insert(image);
//					if (i > 0) {
//						item.setFileCreater(cutImg);
//						bFileMapper.updateByPrimaryKey(item);
//					}
//
//				}
//				return cutImg;
//			}
//		}
//	}

	/**
	 * 获取文件
	 *
	 * @param id
	 * @param w
	 * @param h
	 * @param q
	 * @param resp
	 * @param type
	 * @throws Exception
	 */
	public void getFile(String id, String w, String h, String q,
                        HttpServletResponse resp, String type, HttpServletRequest request)
			throws Exception {
		if (id != null) {
			int dot = id.indexOf(".");
			if (dot != -1) {
				id = id.substring(0, dot);
			}

			BFile item = bFileMapper.selectByPrimaryKey(id);
			if (item == null) {
//				if (id.endsWith(PaidangConst.VIDEO_NORMAL)){
//					getFile(id.substring(0,id.indexOf(PaidangConst.VIDEO_NORMAL)),w,h,q,resp,type,request);
//					return;
//				}else{
//					logger.error("文件不存在;id=" + id);
//					return;
//				}
				logger.error("文件不存在;id=" + id);
				return;
			} else {
				int width = 0, height = 0, quality = 0;
				if (w != null && w.length() > 0) {
					width = Integer.valueOf(w);
				}
				if (h != null && h.length() > 0) {
					height = Integer.valueOf(h);
				}
				if (q != null && q.length() > 0) {
					quality = Integer.valueOf(q);
				}
				File filepath = new File(CoreConstants.FILE_PATH + "/"
						+ item.getFilePath());
				if (OSS.equals(item.getFileBelong())) {
					String url = "https://"
							+ URLEncoder.encode(item.getFilePath(), "utf-8");
					if (width + height != 0
							&& item.getFileMinitype().startsWith("image")) {
						if (height == 0) {
							url += "?x-oss-process=image/resize,w_" + width;
						} else if (height != 0 && width != 0) {
							url += "?x-oss-process=image/resize,w_" + width
									+ ",h_" + height;
						}
						resp.sendRedirect(url);
					} else {
						resp.sendRedirect(url);
					}
					return;
				}
				if (width + height != 0
						&& item.getFileMinitype().startsWith("image")) {
					// 检查目录
					File tempDirectory = new File(CoreConstants.FILE_PATH
							+ "/temp/");
					if (!tempDirectory.isDirectory()) {
						tempDirectory.mkdirs();
					}

					String tf = CoreConstants.FILE_PATH + "/temp/"
							+ item.getFileMd5() + "_" + width + "x" + height
							+ "x" + quality + ".jpg";
					File tempfile = new File(tf);
					if (tempfile.exists()) {
						filepath = tempfile;
					} else {
						ImageUtil.scale(filepath, tempfile, width, height, null);
						filepath = tempfile;
					}

				}

				if (item.getFileMinitype().startsWith("image")) {
					type = "inline";
				}
//				else if (item.getFileMinitype().startsWith("video")) {
//					resp.sendRedirect(CoreConstants.getProperty("video.path") +item.getFilePath());
//					logger.info("转发视频:"+(CoreConstants.getProperty("video.path")+item.getFilePath()));
//					return;
//				}

				resp.setContentType(item.getFileMinitype() + "; charset=utf-8");
				if (!filepath.exists()) {
					logger.error("文件没找到;id=" + id);
					return;
				}
				FileExportUtils.download(request,resp, item.getFileName(),
						item.getFileMinitype(), filepath, type);
			}
		}
	}

	public String getFileForBos(String id){
		if(StringUtils.isBlank(id)){
			return null;
		}
		String url = (String)redisCache.getCacheObject("file:" + id);
		if (url==null){
			BFile item = bFileMapper.selectByPrimaryKey(id);
			if (item==null){
				return null;
			}
			url = CoreConstants.BOS_URL+item.getFilePath();
			redisCache.setCacheObject("file:" + id,url,30, TimeUnit.DAYS);
			return url;
		}else {
			return url;
		}

	}

	public String getFiles(String ids){
		if (com.ruoyi.common.utils.StringUtils.isNotBlank(ids)){
			if (ids.startsWith("http")){
				return ids;
			}
			String[] split = ids.split(",");
			List<String> urls = Lists.newArrayList();
			for (String s : split) {
				String url = getFileForBos(s);
				if (StringUtil.isNotBlank(url)){
					urls.add(url);
				}
			}
			return String.join(",",urls);
		}
		return null;
	}


	public void putObject(byte[] byte1, String fileName){
//		// 获取指定文件
//		File file = new File("/path/to/file.zip");
//		// 获取数据流
//		InputStream inputStream = new FileInputStream("/path/to/test.zip");
		if (client ==null){
			config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
			config.setEndpoint(ENDPOINT);
			client = new BosClient(config);
		}
//		// 以文件形式上传Object
//		PutObjectResponse putObjectFromFileResponse = client.putObject(bucketName, objectKey, file);
//		// 以数据流形式上传Object
//		PutObjectResponse putObjectResponseFromInputStream = client.putObject(bucketName, objectKey, inputStream);
		// 以二进制串上传Object
		long start = System.currentTimeMillis();
		PutObjectResponse putObjectResponseFromByte = client.putObject(BUCKET_NAME, fileName, byte1);
		long end = System.currentTimeMillis();
		logger.info("uploadFile time:{}s,fileName:{}",(end-start)/1000.0,fileName);
		// 以字符串上传Object
//		PutObjectResponse putObjectResponseFromString = client.putObject(bucketName, objectKey, string1);
//
//		// 打印ETag
//		System.out.println(putObjectFromFileResponse.getETag());
	}


	public void putObjectBack(byte[] byte1, String fileName){

		if (client ==null){
			config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
			config.setEndpoint(BACK_ENDPOINT);
			client = new BosClient(config);
		}

		long start = System.currentTimeMillis();
		PutObjectResponse putObjectResponseFromByte = client.putObject(BUCKET_BACK_NAME, fileName, byte1);
		long end = System.currentTimeMillis();
		logger.info("uploadFile time:{}s,fileName:{}",(end-start)/1000.0,fileName);
	}


	public  void putObjectSimple(byte[] bytes,String fileId) throws FileNotFoundException {
		// 初始化一个BosClient
//		BosClientConfiguration config = new BosClientConfiguration();
//		config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
//		config.setEndpoint(ENDPOINT);
//		BosClient client = new BosClient(config);

		if (client ==null){
			config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
			config.setEndpoint(ENDPOINT);
			client = new BosClient(config);
		}

		// 获取指定文件
//		File file = new File("/path/to/file.zip");
//		// 获取数据流
//		InputStream inputStream = new FileInputStream("/path/to/test.zip");
//		byte[] byte0 = new byte[0];
//
//		// 以文件形式上传Object
//		PutObjectResponse putObjectFromFileResponse =
//				client.putObject("bucketName", "file-objectKey", file);
//		// 以数据流形式上传Object
//		PutObjectResponse putObjectResponseFromInputStream =
//				client.putObject("bucketName", "inputStream-objectKey", inputStream);
		// 以二进制串上传Object
		PutObjectResponse putObjectResponseFromByte =
				client.putObject(BUCKET_NAME, fileId, bytes);
		// 以字符串上传Object
//		PutObjectResponse putObjectResponseFromString =
//				client.putObject("bucketName", "string-objectKey", "hello world");

		// 打印ETag
//		System.out.println(putObjectFromFileResponse.getETag());
//		System.out.println(putObjectResponseFromInputStream.getETag());
//		System.out.println(putObjectResponseFromByte.getETag());
//		System.out.println(putObjectResponseFromString.getETag());

		// 关闭客户端
		client.shutdown();
	}

	/**
	 * 文件上传
	 *
	 * @param bytes
	 *            文件
	 * @param fileName
	 *            文件名:xxx.jpg
	 * @param belong
	 *            归属
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public String uploadFile(byte[] bytes, String fileName, String belong) {
		// 文件保存目录路径
		String savePath = CoreConstants.FILE_PATH;
		String md5 = "";
		try {
			md5 = Md5.md5(bytes);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		BFileExample example = new BFileExample();
		example.createCriteria().andFileMd5EqualTo(md5);
		List<BFile> lf = this.selectByExample(example);
		if (lf.size() > 0) {
			return lf.get(0).getFilePath();
		}
		long itemsize = bytes.length;
		// 检查文件大小
		if (itemsize > Long.parseLong(CoreConstants.FILE_MAXSIZE)) {
			return null;
		}
		// 检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
				.toLowerCase();
		String newFileName = UUID.randomUUID().toString().replace("-", "");

		String filePath = null;
		try {
			String minetype = FileExportUtils.getMine(bytes);
			String realdir = null;
			File uploadedFile = null;

			Calendar cal = Calendar.getInstance();
			realdir = minetype + "/" +cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+(cal.get(Calendar.DAY_OF_MONTH));
//			File f = new File(savePath, realdir);
//			if (!f.isDirectory()) {
//				f.mkdirs();
//			}
//			uploadedFile = new File(f.getPath(), newFileName + "."
//						+ fileExt);
//				FileOutputStream fos = new FileOutputStream(uploadedFile);
//				fos.write(bytes);
//				fos.close();
//				minetype = FileExportUtils.getMineType(uploadedFile.getPath());


			BFile ac = new BFile();
			ac.setFileId(newFileName);
			ac.setFileMinitype(minetype);
			ac.setFilePath(realdir + "/" + newFileName + "." + fileExt);
			ac.setFileState(1);
			ac.setFileSize(BigDecimal.valueOf(itemsize));
			ac.setFileMd5(md5);
			ac.setFileCreatetime(new Date());
			ac.setFileName(fileName);
			ac.setFileBelong(belong);
			filePath = ac.getFilePath();
			if (FileExportUtils.isImage(fileName)) {
//				BufferedImage bufferedImage = ImageIO.read(uploadedFile);
//				int width = bufferedImage.getWidth();
//				int height = bufferedImage.getHeight();
//				ac.setFileCreater(width + "x" + height);
			}else if (FileExportUtils.isVideo(minetype)) {
				//上传截图
//				if (StringUtil.isNotBlank(ConstantsCode.FFMPEG_PATH)){
//					FFMpegUtil ffMpegUtil = new FFMpegUtil(uploadedFile);
//					//视频截图
//					String cutImgName = newFileName+ PaidangConst.VIDEO_CUR_IMG+".jpg";
//					File cutImgFile = new File(f.getPath(),cutImgName);
//					ffMpegUtil.makeScreenCut(cutImgFile);
//					BFile image = new BFile();
//					image.setFileId(newFileName+PaidangConst.VIDEO_CUR_IMG);
//					image.setFileMinitype("image/jpeg");
//					image.setFilePath(realdir + "/" + cutImgName);
//					image.setFileState(1);
//					image.setFileCreatetime(new Date());
//					image.setFileName(cutImgName);
//					image.setFileCreater("640x360");
//					image.setFileBelong(BFileService.LOCAL);
//					insert(image);
////					ac.setFileCreater(cutImgName);
//					int time = ffMpegUtil.getRuntime();
//					if (itemsize / time > 100000){
//						//视频
//						ThreadUtil.execute(new VideoConRun(ffMpegUtil,f.getAbsolutePath(),realdir,newFileName, this));
//					}
//
//				}
			}

			if (FileConstants.FILE_MODE.equals(OSS)) {
//				boolean b = MyOssClient.putObject(ac.getFilePath(), minetype,
//						bytes, null, ac.getFileName());
				putObject(bytes,ac.getFilePath());
				ac.setFileBelong(BUCKET_NAME+"."+ENDPOINT);
				insert(ac);
//				if (b) {
//					ac.setFileBelong(MyOssClient.OSS_BUCKET + "."
//							+ MyOssClient.OSS_ENDPOINT);
//					insert(ac);
//					if (uploadedFile.exists()) {
//						uploadedFile.delete();
//					}
//				} else {
//					return null;
//				}
			} else {
				ac.setFileBelong(LOCAL);
				this.insert(ac);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return null;
		}
		return filePath;
	}

	/**
	 * 获取图片长宽
	 *
	 * @param fileId
	 * @return
	 */
//	public String getSize(String fileId) {
//		String size = CacheSupport.get("imageSizeCache",
//				"BFileService.getSize." + fileId, String.class);
//		if (StringUtil.isBlank(size)) {
//			BFile file = this.bFileMapper.selectByPrimaryKey(fileId);
//			if (file != null && StringUtil.isNotBlank(file.getFileCreater())) {
//				CacheSupport.put("imageSizeCache", "BFileService.getSize."
//						+ fileId, file.getFileCreater());
//				return file.getFileCreater();
//			}
//			return "";
//		}
//		return size;
//	}

	/**
	 * 格式化图片ids:id|w|h,id|w|h
	 *
	 * @param ids
	 * @return
	 */
//	public String format(String ids) {
//		if (StringUtil.isBlank(ids))
//			return "";
//		String[] idArray = ids.split(",");
//		String size = "";
//		List<String> strs = new ArrayList<String>();
//		for (String id : idArray) {
//			size = getSize(id);
//			if (StringUtil.isNotBlank(size)) {
//				id = id + "|" + size.replace("x", "|");
//			} else {
//				id = id + "||";
//			}
//			strs.add(id);
//		}
//		return StringUtil.listToString(strs);
//	}

	public static String getTomcatWebappsPath(HttpServletRequest request){
		String tomcatRoot = request.getSession().getServletContext().getRealPath("/");
		String[] foo = tomcatRoot.split("/");
		StringBuilder tomcatWebAppsBuilder = new StringBuilder();
		int i = 0;
		for(String paths : foo){
			++i;
			if(i != foo.length){
				tomcatWebAppsBuilder.append(paths);
				tomcatWebAppsBuilder.append("/");
			}
		}
		String tomcatWebApps = tomcatWebAppsBuilder.toString();
		return tomcatWebApps;
	}

	public String uploadHeadImg(String url) {
		try {
			String savePath = UUID.randomUUID().toString();
			byte[] bytes = URLUtil.download(url);
			String type = FileTypeUtil.getType(bytes);
			if (type != null) {
				savePath = savePath + "." + type.toLowerCase();
			}

			return this.uploadFile((byte[])bytes, (String)savePath, (String)null);
		} catch (Exception var5) {
			return null;
		}
	}

	/**
	 * 从网络Url中读取文件
	 * @param urlStr 文件URL地址
	 * @throws IOException
	 */
	public  String downLoadFromUrl(String urlStr,String contractNo) throws IOException{

		HttpConnector httpConnector = new HttpConnector();
		httpConnector.init();
		byte[] bytes = httpConnector.getFile(urlStr);

//		//把地址转换成URL对象
//		URL url = new URL(urlStr);
//		//创建http链接
//		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//		//设置超时间为3秒
//		conn.setConnectTimeout(10*1000);
//		//防止屏蔽程序抓取而返回403错误
//		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//		//得到输入流
//		InputStream inputStream = conn.getInputStream();
//		byte[] bytes = toByteArray(inputStream);
		//截取链接中的文件名
		String fileName= contractNo+".pdf";
		putObject(bytes,"contract/"+fileName);
		//请求OSS方法
//		URL resUrl = OSSUploadImage(inputStream,fileName,"imagesTest/");

		return CoreConstants.BOS_URL+"contract/"+fileName;
	}

	public static byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}
		return output.toByteArray();
	}


	public void transferFile(String startTime,String endTime) throws Exception{
//		http://baidu.paidangwang.net/admin//download?id=9497c7de6bc24a8e93721a4f7d1ddde0
//		http://baidu.paidangwang.net/admin//download?id=0768268a2d0b4ba9ad0ac0de4c82e9a6_normal
		//0768268a2d0b4ba9ad0ac0de4c82e9a6_normal
		String prefix = "http://baidu.paidangwang.net/admin//download?id=";
		//
		BaseUtils.checkBlankParam(startTime);
		String time = "2021-01-22 18:31:49";
		if (StringUtils.isBlank(endTime)){
			endTime = time;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BFileExample example = new BFileExample();
		example.createCriteria().andFileCreatetimeLessThanOrEqualTo(sdf.parse(endTime)).andFileCreatetimeGreaterThanOrEqualTo(sdf.parse(startTime));
		example.setOrderByClause("file_createtime asc");
		List<BFile> bFiles = selectByExample(example);
		int i = 1;
		for (BFile file : bFiles) {
			try {
				String fileId= file.getFileId();
				byte[] fileStream = FileUtils.getFileStream(prefix + fileId);
				putObject(fileStream,file.getFilePath());
				logger.info("upload:"+i + "  "+ file.getFilePath() + "  "+ sdf.format(file.getFileCreatetime()));
				i++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

//		BFile bFile = selectByPrimaryKey("0768268a2d0b4ba9ad0ac0de4c82e9a6_normal");
//		String fileId= bFile.getFileId();
//		byte[] fileStream = FileUtils.getFileStream(prefix + fileId);
//		putObject(fileStream,bFile.getFilePath());
//		System.out.println(bFile.getFilePath());
	}
}