package com.example.gwtapp.server.service.api;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface FileStorageService {

    String store(MultipartFile file) throws IOException;

    Resource load(String fileName);

    Path getFilePath(String fileName);
}
