package com.example.mapper;

import com.example.entity.Admit;
import com.example.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdmitMapper {
    @Select("select * from admit where id=#{id}")
    public Admit getAdimitById(@Param("id") int id);
}
