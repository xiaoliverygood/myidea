package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {
    @Select("select id from activity where name=#{name}")
    public Integer getActivityIdByName(String name);
    @Select("select userEmail from LinkActirity where id=#{id}")
    public List<String> getActivityUserById(@Param("id") int id);
    @Select("SELECT * FROM activity WHERE name LIKE #{nameLike}")
    public List<Activity> selectResult(@Param("nameLike") String nameLike);
}




