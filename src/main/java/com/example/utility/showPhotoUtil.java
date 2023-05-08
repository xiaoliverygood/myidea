package com.example.utility;
import org.springframework.stereotype.Component;
@Component
public class showPhotoUtil {
    public String getPhotoByName(String name) {
      return QiniuUtil.getImageUrl(name);
    }

}
