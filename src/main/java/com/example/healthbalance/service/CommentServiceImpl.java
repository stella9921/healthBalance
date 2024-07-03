package com.example.healthbalance.service;

import com.example.healthbalance.domain.dto.CommentDTO;
import com.example.healthbalance.domain.dto.CommentListDTO;
import com.example.healthbalance.domain.vo.CommentVO;
import com.example.healthbalance.mapper.CommentMapper;

import java.util.List;

public class CommentServiceImpl implements CommentService {


    private CommentMapper commentMapper;
    @Override
    public List<CommentListDTO> getCommentById(Long boardId) {
        return commentMapper.selectCommentById(boardId);
    }

    @Override
    public void saveComment(CommentDTO commentDTO) {
    commentMapper.insertComment(CommentVO.toEntity(commentDTO));
    }

    @Override
    public void deleteComment(Long commentId) {
        commentMapper.deleteComment(commentId);

    }

    @Override
    public void updateComment(CommentDTO commentDTO) {
        commentMapper.updateComment(CommentVO.toEntity(commentDTO));

    }
}
