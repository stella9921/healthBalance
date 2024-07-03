package com.example.healthbalance.service;


import com.example.healthbalance.domain.dto.CommentDTO;
import com.example.healthbalance.domain.dto.CommentListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface CommentService {

    //해당 게시글의 댓글 목록 보기
    List<CommentListDTO> getCommentById(Long boardId);

    void saveComment(CommentDTO commentDTO);

    void deleteComment(Long commentId);

    void updateComment(CommentDTO commentDTO);


}
