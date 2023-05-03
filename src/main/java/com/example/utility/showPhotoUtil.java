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
        String filePath = "imageActivity/"+name+".png";
        byte[] imageBytes = new byte[0];
        try {
            imageBytes = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String base64EncodedImage = Base64.getEncoder().encodeToString(imageBytes);
        return base64EncodedImage;
    }

}
