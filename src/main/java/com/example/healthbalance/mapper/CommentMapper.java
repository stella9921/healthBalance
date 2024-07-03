package com.example.healthbalance.mapper;

import com.example.healthbalance.domain.dto.CommentListDTO;
import com.example.healthbalance.domain.vo.CommentVO;

import java.util.List;

public interface CommentMapper {

    //해당 게시글의 댓글 목록 보기
    List<CommentListDTO> selectCommentById(Long boardId);

    void insertComment(CommentVO commentVO);

    void deleteComment(Long commentId);

    void updateComment(CommentVO commentVO);



}
