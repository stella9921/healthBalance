package com.example.healthbalance.service;

import com.example.healthbalance.domain.dto.FileDTO;
import com.example.healthbalance.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileMapper fileMapper;


    @Override
    public List<FileDTO> getFileListByBoardId(Long boardId) {
        return fileMapper.selectFileList(boardId);
    }
}
