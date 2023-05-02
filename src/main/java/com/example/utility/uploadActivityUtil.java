package com.example.utility;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.OutputStream;

@Component

public class uploadActivityUtil {
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
}
