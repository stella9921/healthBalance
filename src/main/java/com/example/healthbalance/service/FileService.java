package com.example.healthbalance.service;

import com.example.healthbalance.domain.dto.FileDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FileService {


    List<FileDTO> getFileListByBoardId(Long boardId);

    FileDTO getFileById(Long fileId);
}
