package com.parkging.helloblog.web.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Base64;

public class BASE64DecodedMultipartFile implements MultipartFile {
    private byte[] imgContent;

    private String based64ImgContent;

    private String name;
    private String originalFilename;
    private String contentType;
    public BASE64DecodedMultipartFile(byte[] imgContent, String name, String originalFilename, String contentType) {
        this.imgContent = imgContent;
        this.based64ImgContent = new String(Base64.getEncoder().encode(imgContent));
        this.name = name;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
    }
    public BASE64DecodedMultipartFile(String based64ImgContent, String name, String originalFilename, String contentType) {
        this.imgContent = Base64.getDecoder().decode(based64ImgContent);
        this.based64ImgContent = based64ImgContent;
        this.name = name;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
    }

    public byte[] getImgContent() {
        return this.imgContent;
    }
    public String getBased64ImgContent() {
        return this.based64ImgContent;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getOriginalFilename() {
        return this.originalFilename;
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }

    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(imgContent);
    }

    @Override
    public String toString() {
        return this.getName() + ":size(" + this.getSize()+")";
    }
}