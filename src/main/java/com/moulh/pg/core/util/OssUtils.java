package com.moulh.pg.core.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName : OssUtils
 * @Author : moulh
 * @Date : 2021-07-12 15:24
 * @Version : V1.0
 * @Description :
 */
@Slf4j
public class OssUtils {
    /**
     * 获取阿里云OSS客户端对象
     * */
    public static final OSSClient getOSSClient(String ENDPOINT, String ACCESS_KEY_ID, String ACCESS_KEY_SECRET){
        // 创建ClientConfiguration实例
        return new OSSClient(ENDPOINT,ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    /**
     * 新建Bucket  --Bucket权限:私有
     * @param bucketName bucket名称
     * @return true 新建Bucket成功
     * */
    public static final boolean createBucket(OSSClient client, String bucketName){
        Bucket bucket = client.createBucket(bucketName);
        return bucketName.equals(bucket.getName());
    }

    /**
     * 删除Bucket
     * @param bucketName bucket名称
     * */
    public static final void deleteBucket(OSSClient client, String bucketName){
        client.deleteBucket(bucketName);
        log.info("删除" + bucketName + "Bucket成功");
    }

    public static final String uploadObject2OSS2(OSSClient client, File file, String bucketName, String diskName) {
        String resultStr = null;
        try {
            InputStream is = new FileInputStream(file);
            String fileName = file.getName();
            Long fileSize = file.length();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(is.available());
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");
            metadata.setContentEncoding("utf-8");
            metadata.setContentType(getContentType(fileName));
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件
            PutObjectResult putResult = client.putObject(bucketName, diskName + fileName, is, metadata);
            //解析结果
            resultStr = putResult.getETag();
        } catch (Exception e) {
            log.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;

    }

    /**
     * 向阿里云的OSS存储中存储文件  --file也可以用InputStream替代
     * @param client OSS客户端
     * @param multipartFile 上传文件
     * @param bucketName bucket名称
     * @param diskName 上传文件的目录  --bucket下文件的路径
     * @return String 唯一MD5数字签名
     * */
    public static final String uploadObject2OSS(OSSClient client, MultipartFile multipartFile, String bucketName, String diskName) {
        String resultStr = null;
        try {
            CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) multipartFile;
            DiskFileItem fileItem =(DiskFileItem) commonsMultipartFile.getFileItem();
            InputStream inputStream = fileItem.getInputStream();
            String fileName = multipartFile.getOriginalFilename();
            Long fileSize = multipartFile.getSize();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(inputStream.available());
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");
            metadata.setContentEncoding("utf-8");
            metadata.setContentType(getContentType(fileName));
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件
            PutObjectResult putResult = client.putObject(bucketName, diskName + fileName, inputStream, metadata);
            //解析结果
            resultStr = putResult.getETag();
        } catch (Exception e) {
            log.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;

    }

    /**
     * 根据key获取OSS服务器上的文件输入流
     * @param client OSS客户端
     * @param bucketName bucket名称
     * @param diskName 文件路径
     * @param key Bucket下的文件的路径名+文件名
     */
    public static final InputStream getOSS2InputStream(OSSClient client, String bucketName, String diskName, String key){
        OSSObject ossObj = client.getObject(bucketName, diskName + key);
        return ossObj.getObjectContent();
    }

    /**
     * 根据key删除OSS服务器上的文件
     * @param client OSS客户端
     * @param bucketName bucket名称
     * @param diskName 文件路径
     * @param key Bucket下的文件的路径名+文件名
     */
    public static void deleteFile(OSSClient client, String bucketName, String diskName, String key){
        client.deleteObject(bucketName, diskName + key);
        log.info("删除" + bucketName + "下的文件" + diskName + key + "成功");
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static final String getContentType(String fileName){
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if("bmp".equalsIgnoreCase(fileExtension)) return "image/bmp";
        if("gif".equalsIgnoreCase(fileExtension)) return "image/gif";
        if("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension)  || "png".equalsIgnoreCase(fileExtension) ) return "image/jpeg";
        if("html".equalsIgnoreCase(fileExtension)) return "text/html";
        if("txt".equalsIgnoreCase(fileExtension)) return "text/plain";
        if("vsd".equalsIgnoreCase(fileExtension)) return "application/vnd.visio";
        if("ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) return "application/vnd.ms-powerpoint";
        if("doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) return "application/msword";
        if("xml".equalsIgnoreCase(fileExtension)) return "text/xml";
        if("zip".equalsIgnoreCase(fileExtension)) return "text/zip";
        return "text/html";
    }

    public static List<MultipartFile> getFiles(HttpServletRequest request) {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            //文件集合
            MultiValueMap<String, MultipartFile> multiValueMap = multipartRequest.getMultiFileMap();
            Iterator<String> iterator = multipartRequest.getFileNames();
            while (iterator.hasNext()) {
                //1.取得上传文件
                String fieldName = iterator.next();
                List<MultipartFile> multipartFiles = multiValueMap.get(fieldName);
                return multipartFiles;
            }
        }
        return null;
    }

    public static byte[] readStream(InputStream inputStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = inputStream.read(buffer)) != -1){
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inputStream.close();
        return outStream.toByteArray();
    }

    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public static String getUrl(OSSClient client, String key, String bucketName) {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = client.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }
}
