package com.example.healthbalance.domain.vo;


import com.example.healthbalance.domain.dto.FileDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@ToString
@NoArgsConstructor
public class FileVO {


    private Long fileId;
    //실제 파일 명
    private String originalFileName;
    //경로, 우리가 다운로드 진행시
    private String storedFileName;

    private Long fileSize;

    private LocalDateTime uploadTime;
    //fk, 어느 게시물의 첨부파일이냐
    private Long boardId;

    @Builder
    public FileVO(Long fileId, String originalFileName, String storedFileName, Long fileSize, LocalDateTime uploadTime, Long boardId) {

        this.fileId = fileId;
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.fileSize = fileSize;
        this.uploadTime = uploadTime;
        this.boardId = boardId;

    }
    public static FileVO toEntity(FileDTO fileDTO) {
        return FileVO.builder().fileId(fileDTO.getFileId())
                .originalFileName(fileDTO.getOriginalFileName())
                .storedFileName(fileDTO.getStoredFileName())
                .fileSize(fileDTO.getFileSize())
                .boardId(fileDTO.getBoardId())
                .build();
    }

}
