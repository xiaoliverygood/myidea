package com.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadTheFile {
    public static String TeacherUpload(String name, MultipartFile file) throws IOException {
        String fileName = "L:\\桌面文件\\plan of winter holiday\\MyFirstProject\\First\\File"+name+".txt";
        FileOutputStream fileObj = new FileOutputStream(fileName);//请求头将文件上传到了file里面，我们用文件流转存
        long fileSize = file.getSize();
        byte[] buffer = new byte[1024];
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
