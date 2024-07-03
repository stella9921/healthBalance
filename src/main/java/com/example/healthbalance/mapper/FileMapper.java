package com.example.healthbalance.mapper;

import com.example.healthbalance.domain.dto.FileDTO;
import com.example.healthbalance.domain.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    //첨부파일 insert
    //첨부파일은 게시글이 추가될때 날아가야함.

    void insertFile(FileVO fileVo);

    List<FileDTO> selectFileList(Long boardId);

    //첨부파일 db에서 삭제.
    //게시글 어 ㅂ데잍 ㅡ할때 사용
    void deleteFile(Long boardId);
}
