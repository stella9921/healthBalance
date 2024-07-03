package com.example.healthbalance.domain.dto;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class BoardDTO {

    private Long boardId;
    private String boardTitle;
    private String providerId;
    private String boardContent;
    private int boardViews;
    private LocalDateTime boardRegisterDate;
    private LocalDateTime boardUpdateDate;
}
