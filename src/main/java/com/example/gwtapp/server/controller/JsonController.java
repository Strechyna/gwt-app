package com.example.gwtapp.server.controller;

import com.example.gwtapp.server.controller.response.ResponseMessage;
import com.example.gwtapp.server.service.impl.JsonServiceImpl;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("places/json")
public class JsonController extends CommonFileController {

    public JsonController(JsonServiceImpl service) {
        super(service);
    }

    @PostMapping("/import")
    public ResponseEntity<ResponseMessage> importFile(@RequestParam("file") MultipartFile file) {
        return super.importFile(file);
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> downloadFile() {
        return super.downloadFile();
    }
}
