package com.example.Mapper;

import com.example.Entity.Student;
import com.example.Entity.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface TeacherMapper {
    @Insert("INSERT INTO `firstproject`.`Teacher`(`tid`, `name`, `email`, `lesson`, `password`) VALUES (#{Teacher.tid}, #{Teacher.name}, #{Teacher.email},#{Teacher.lesson},#{Teacher.password})")
    void insertTeacher(@Param("Teacher")Teacher teacher);
    //注意，不需要返回值，不然会出现报错的情况，但是数据库里面是增加了
    @Select("select * from Teacher where tid=#{tid}")
    public Teacher getTeacherById(int tid);
    @Select("select * from Teacher where email=#{email}")
    public Teacher getTeacherByEmile(String email);
    @Select("select * from Teacher where lesson=#{lesson}")
    public List<Teacher> getTeacherByLesson(String lesson);
    @Delete("DELETE FROM Teacher WHERE tid=#{tid}")
    void deleteTeacherBySid(int tid);
}
