package com.example.utility;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.util.Base64;

@Component

public class uploadActivityUtil {
    private static final String OWNER_NAME = "xiaoliverygood";
    private static final String REPO_NAME = "iVolunteer";
    public String uploadFile(MultipartFile file,String name) {
        try {
            String originalFileName = file.getOriginalFilename(); // 获取原始文件名
            String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1); // 获取文件扩展名
            String newFileName = name + "." + extension; // 根据扩展名生成新的文件名
// 设置文件保存路径
            String filePath = "imageActivity/" + newFileName;
// 创建文件输出流
            File out = new File(filePath);
            FileUtils.copyInputStreamToFile(file.getInputStream(), out);
            return "文件上传成功！";
        } catch (Exception e) {
            return "文件上传失败：" + e.getMessage();
        }
    }
//    public String uploadFile(MultipartFile file,String name) {
//
//        // 获取文件内容的 Base64 编码
//        byte[] fileContent = file.getBytes();
//        String fileContentBase64 = Base64.getEncoder().encodeToString(fileContent);
//
//        // 获取 Gitee 的 access token
//        GiteeTokenResult tokenResult = GiteeApi.getAccessToken("your_client_id", "your_client_secret", "your_authorization_code", "your_redirect_uri");
//
//        // 上传文件到 Gitee
//        GiteeApi.createFile(tokenResult.getAccessToken(), GiteeConstants.API_URL_BASE, OWNER_NAME, REPO_NAME, "imageActivity/" + name, fileContentBase64, "commit message");
//
//        // 返回文件的访问 URL
//        return "https://gitee.com/" + OWNER_NAME + "/" + REPO_NAME + "/raw/master/imageActivity/" + name;
//
//    }
}
