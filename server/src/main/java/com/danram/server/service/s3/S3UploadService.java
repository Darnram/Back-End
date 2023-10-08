package com.danram.server.service.s3;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3UploadService {
    String upload(MultipartFile multipartFile, String dirName) throws IOException;
}
