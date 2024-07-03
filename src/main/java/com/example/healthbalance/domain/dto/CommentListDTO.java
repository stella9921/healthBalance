package com.example.healthbalance.domain.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentListDTO {
    private Long commentId;
    private Long boardId;
    private String providerId;
    private String commentContent;
    private LocalDateTime commentRegisterDate;
    private LocalDateTime commentUpdateDate;
    private String name;

}
