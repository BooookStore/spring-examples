package com.example.spring.security.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface RoleMapper {

    List<RoleTable> findById(int id);

}
