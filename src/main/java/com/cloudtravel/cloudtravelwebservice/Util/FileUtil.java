package com.cloudtravel.cloudtravelwebservice.Util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    private static final String UPLOAD_DIRECTORY = "/srv/ftp/www/images/";

    private static final String[] IMAGE_TYPES = {"png", "jpg", "gif"};

    private static String fileUpload(MultipartFile file, String uploadDir, String[] types) {
        try {
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String fileName = file.getOriginalFilename();
            String fileType = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".") + 1) : null;
            if (fileType != null) {
                for (String str : types) {
                    if (str.toUpperCase().equals(fileType.toUpperCase())) {
                        fileName = "cloudtravel_" + String.valueOf(System.currentTimeMillis()) + "." + fileType;
                        file.transferTo(new File(uploadDir + fileName));
                        Runtime.getRuntime().exec("chmod 644 " + uploadDir + fileName);
                        return "http://47.100.253.251:8081/images/" + fileName;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String imageUpload(MultipartFile file) {
        return fileUpload(file, UPLOAD_DIRECTORY, IMAGE_TYPES);
    }
}
