package com.example.restTemplateDemo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.restTemplateDemo.service.S3Service;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    private S3Service s3Service;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {

        if (!file.getOriginalFilename().endsWith(".docx")) {
            return ResponseEntity.badRequest().body("Only Word documents allowed");
        }

        String fileName = s3Service.uploadFile(file);
        return ResponseEntity.ok("Uploaded: " + fileName);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> download(@PathVariable String fileName) {

        byte[] data = s3Service.downloadFile(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(data);
    }

    @GetMapping("/presign")
    public ResponseEntity<String> generateUrl(@RequestParam String fileName) {

        String url = s3Service.generatePresignedUrl(fileName);

        return ResponseEntity.ok(url);
    }

}
