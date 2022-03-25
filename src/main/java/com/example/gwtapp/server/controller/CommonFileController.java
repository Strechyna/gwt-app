package com.example.gwtapp.server.controller;

import com.example.gwtapp.server.controller.response.ResponseMessage;
import com.example.gwtapp.server.service.api.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public class CommonFileController {

    private final FileService service;

    public CommonFileController(FileService service) {
        this.service = service;
    }

    public ResponseEntity<ResponseMessage> importFile(MultipartFile file) {
        try {
            service.importFile(file);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(e.getMessage()));
        }

    }

    public ResponseEntity<Resource> downloadFile() {
        try {
            Resource resource = service.exportFile();
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}
