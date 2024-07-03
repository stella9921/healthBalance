package com.example.healthbalance.service;

import com.example.healthbalance.domain.dto.BoardDTO;
import com.example.healthbalance.domain.dto.BoardDetailDTO;
import com.example.healthbalance.domain.dto.BoardListDTO;
import com.example.healthbalance.domain.dto.FileDTO;
import com.example.healthbalance.domain.oauth.CustomOAuth2User;
import com.example.healthbalance.domain.vo.BoardVO;
import com.example.healthbalance.domain.vo.FileVO;
import com.example.healthbalance.mapper.BoardMapper;
import com.example.healthbalance.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    private final FileMapper fileMapper;



    @Override
    public List<BoardListDTO> getBoardList(int page, int pageSize) {

        int startRow = (page-1)*pageSize;
        int endRow = page*pageSize;

        return boardMapper.selectAll(startRow, endRow);
    }

    @Override
    public int getBoardListCount() {

        return boardMapper.countBoard();
    }

    @Override
    public void saveBoard(BoardDTO board, List<MultipartFile> files) {
        Long boardId = boardMapper.getSeq();
        board.setBoardId(boardId);
        boardMapper.saveBoard(board);

        saveFile(boardId,files);


    }

    //게시글 상세정보 가져오는 함수
    @Override
    @Transactional
    public BoardDetailDTO getBoardById(Long boardId, CustomOAuth2User customOAuth2User) {

        BoardDetailDTO board = boardMapper.selectBoardDetail(boardId);
    //조회수 상승위한거
        if(customOAuth2User != null || !customOAuth2User.getProviderId().equals(board.getProviderId())) {
            boardMapper.plusView(boardId);
        }

        return board;

    }

    //수정폼으로 이동시 가져갈 board select
    @Override
    public BoardDetailDTO goUpdateBoard(Long boardId) {
        return boardMapper.selectBoardDetail(boardId);

    }

    @Override
    public void updateBoard(BoardDTO board, List<MultipartFile> files) {
        boardMapper.updateBoard(BoardVO.toEntity(board));
        //원래 있던 첨부파일 삭제
        fileMapper.deleteFile(board.getBoardId());

        //그냥 files insert
        saveFile(board.getBoardId(),files);
    }

    @Override
    public void saveFile(Long boardId, List<MultipartFile> files) {

        //현재 날짜를 기반으로 폴더 경로 생성
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String datePath = now.format(formatter);


        for(MultipartFile file : files) {
            //방어코드
            if(file.isEmpty()) continue;
            String originalFilename = file.getOriginalFilename();
            String storedFileName = UUID.randomUUID().toString().replaceAll("-","")+"_"+originalFilename;
            Long fileSize = file.getSize();

            try{
                //파일 저장 경로 설정
                Path directoryPath = Paths.get("C:/upload/"+datePath);
                if(!Files.exists(directoryPath)){
                    Files.createDirectories(directoryPath);
                }
                Path filePath = directoryPath.resolve(storedFileName);
                //파일 저장
                Files.copy(file.getInputStream(), filePath);

                FileDTO fileDTO = new FileDTO();
                fileDTO.setBoardId(boardId);
                fileDTO.setOriginalFileName(originalFilename);
                fileDTO.setStoredFileName(directoryPath+"/"+storedFileName);
                fileDTO.setFileSize(fileSize);

                fileMapper.insertFile(FileVO.toEntity(fileDTO));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public void deleteBoard(Long boardId) {
        boardMapper.deleteBoard(boardId);

    }


}
