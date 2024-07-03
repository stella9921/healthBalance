package com.example.healthbalance.mapper;


import com.example.healthbalance.domain.dto.UsersDTO;
import com.example.healthbalance.domain.vo.UsersVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UsersDTO findByProviderId(String providerId);
    void saveUser(UsersVO vo);


    void updateUser(UsersVO vo);

}
