package com.example.healthbalance.domain.dto;

import lombok.Data;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class FileDTO {


    private Long fileId;
    //실제 파일명
    //게시판 상세보기 페이지 에서 실제 노출할 이름
    private String originalFileName;

    //경로, 우리가 다운로드 진행시
    private String storedFileName;

    private Long fileSize;
    private LocalDateTime uploadTime;

    //fk 어느 게시물의 첨부파일이냐
    private Long boardId;



}
