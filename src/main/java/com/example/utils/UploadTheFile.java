package com.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class UploadTheFile {

    public static String TeacherUpload(String name, MultipartFile file) throws IOException {
        File fileObj = new File(name+"introduction.html");//请求头将文件上传到了file里面，我们用文件流转存
        file.transferTo(fileObj);//文件上传到了file，我们用它对象的方法，进行转成文件流，文件流是我们自己的地址
        System.out.println("用户上传的文件已保存到："+fileObj.getAbsolutePath());
        return "文件上传成功！";

    }
}
