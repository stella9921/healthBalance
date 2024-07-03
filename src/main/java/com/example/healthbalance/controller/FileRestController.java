package com.example.healthbalance.controller;


import com.example.healthbalance.domain.dto.FileDTO;
import com.example.healthbalance.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

@RestController
@RequestMapping("/download")
@RequiredArgsConstructor
public class FileRestController {

    private final FileService fileService;


    @GetMapping("/{fileId}")
    public ResponseEntity<Resource> download(@PathVariable("fileId")Long fileId){


        FileDTO file = fileService.getFileById(fileId);


        Path filePath = Path.of(file.getStoredFileName());

        Resource resource = new FileSystemResource(filePath);

        if(!resource.exists()){
            return ResponseEntity.notFound().build();
        }

        String encodedFileName = URLEncoder.encode(file.getOriginalFileName(),
                StandardCharsets.UTF_8).replace("+", "%20");


        String contentDisposition="attachment;filename=\"" + encodedFileName + "\"";
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition)
                .body(resource);

    }



}
