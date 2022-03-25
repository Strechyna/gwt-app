package com.example.gwtapp.server.service.impl;

import com.example.gwtapp.server.service.api.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageServiceImpl(@Value("${file.temp-dir}") String tempDir) throws IOException {
        this.fileStorageLocation = Paths.get(tempDir)
                .toAbsolutePath().normalize();
        Files.createDirectories(this.fileStorageLocation);
    }

    public String store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path targetLocation = getFilePath(fileName);

        try (InputStream fileInputStream = file.getInputStream()) {
            Files.copy(fileInputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
        }

        return targetLocation.toString();
    }

    public Resource load(String fileName) {
        Path filePath = getFilePath(fileName);
        return new FileSystemResource(filePath.toString());
    }

    public Path getFilePath(String fileName) {
        return this.fileStorageLocation.resolve(fileName).normalize();
    }

}
