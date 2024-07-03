package com.example.healthbalance.mapper;


import com.example.healthbalance.domain.dto.BoardDTO;
import com.example.healthbalance.domain.dto.BoardDetailDTO;
import com.example.healthbalance.domain.dto.BoardListDTO;
import com.example.healthbalance.domain.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    //게시판 목록
    List<BoardListDTO> selectAll(int startRow,int endRow);

    //게시판 총 갯수
    //페이징 처리할 때 사용할 쿼리
    int countBoard();


    //다음 시퀀스 가져오기
    //게시글 작성 시 사용할 쿼리
    long getSeq();

    // 게시글 작성
    void saveBoard(BoardDTO board);
    //게시글 상세보기
    BoardDetailDTO selectBoardDetail(Long boardId);

    void plusView(Long boardId);

    // 게시글 수정하기
    void updateBoard(BoardVO boardVO);

    //게시글 삭제하기
    void deleteBoard(Long boardId);


}
