package com.junjunguo.shr.util;

import com.junjunguo.shr.model.Video;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 01/11/15.
 */
public class FileHandler {

    //TODO: enable resume on broken upload
    public String save(Video v, MultipartFile mFile) {
        String path =
                Constant.VIDEO_FILE_PATH + v.getOwner().getId() + "/";
        // video name = id + uploaded time
        String name = v.getId() + System.currentTimeMillis() + "." + v.getFileExtension();
        File   file = new File(path);
        file.mkdirs();
        try {
            byte[] bytes = mFile.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(path + name)));
            stream.write(bytes);
            stream.close();
            return path + name;
        } catch (Exception e) {
            return "Error You failed to upload " + path + "; exception: " + e.getMessage();
        }
    }

    public String SaveFromStr(Video v, String input) {
        String path =
                Constant.VIDEO_FILE_PATH + v.getOwner().getId() + "/";
        String name = System.currentTimeMillis() + "." + v.getFileExtension();
        try {
            File file = new File(path);
            file.mkdirs();
            log("file: " + file.getAbsolutePath());
            FileOutputStream out = new FileOutputStream(path + name);
            out.write(input.getBytes());
            out.flush();
            out.close();
            return path + name;
        } catch (Exception e) {
            return "Error You failed to upload " + path + "; exception: " + e.getMessage();
        }
    }

    //    public String saveFile(long userId, MultipartFile multipartFile) {
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

    public void log(String s) {
        System.out.println(this.getClass().getSimpleName() + "- - - - - - " + s);
    }
}
