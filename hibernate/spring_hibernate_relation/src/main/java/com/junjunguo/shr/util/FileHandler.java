package com.junjunguo.shr.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 01/11/15.
 */
public class FileHandler {
    //TODO: enable resume on broken upload
    public String saveFile(long userId, MultipartFile multipartFile) {
        String path =
                Constant.VIDEO_FILE_PATH + "/" + userId + "/" + System.currentTimeMillis();
        byte[]           buffer     = new byte[Constant.BUFFER_SIZE];
        File             outputFile = new File(path);
        FileInputStream  reader     = null;
        FileOutputStream writer     = null;
        int              totalBytes = 0;
        try {
            outputFile.createNewFile();
            reader = (FileInputStream) multipartFile.getInputStream();
            writer = new FileOutputStream(outputFile);
            int count;
            while ((count = reader.read(buffer)) != -1) {
                writer.write(buffer);
                totalBytes += count;
            }
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
