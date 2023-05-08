package com.example.utility;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class showPhotoUtil {
    public String getPhotoByName(String name) {
      return QiniuUtil.getImageUrl(name);
    }

}
