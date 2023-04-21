package com.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadTheFile {
    public static String TeacherUpload(String name, MultipartFile file) throws IOException {
        //获取文件的原始名称
       // String fileName = file.getOriginalFilename();
// 获取文件的扩展名
        //String extension = org.apache.commons.io.FilenameUtils.getExtension(fileName);
        String fileName2 = "L:\\桌面文件\\plan of winter holiday\\MyFirstProject\\First\\File"+".png";
        FileOutputStream fileObj = new FileOutputStream(fileName2);//请求头将文件上传到了file里面，我们用文件流转存
        long fileSize = file.getSize();
        byte[] buffer = new byte[8192];
        int len;
        while ((len = file.getInputStream().read(buffer)) > 0) {
            fileObj.write(buffer, 0, len);
            fileSize -= len;
            if (fileSize <=0) {
                break;
            }
        }
        return "文件上传成功！";
    }
}
