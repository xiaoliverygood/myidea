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
    public String uploadFile(MultipartFile file) {
        try {
            // 获取上传的文件名
            String fileName = file.getOriginalFilename();
// 设置文件保存路径
            String filePath = "L:\\桌面文件\\plan of winter holiday\\MyFirstProject\\iVolunteer\\imageActivity\\" + fileName;
// 创建文件输出流
            File out = new File(filePath);
            FileUtils.copyInputStreamToFile(file.getInputStream(), out);
            return "文件上传成功！";
        } catch (Exception e) {
            return "文件上传失败：" + e.getMessage();
        }
    }
}
