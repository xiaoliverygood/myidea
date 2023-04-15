package com.example.mapper;

import com.example.entity.StudentResult;
import com.example.entity.Teacher;
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

    @Update("UPDATE Teacher SET name = #{name} WHERE tid =#{tid}")
    public void UpdateTeacherNameById(@Param("tid") int tid, @Param("name") String Newname);
    @Update("UPDATE Teacher SET password = #{password} WHERE tid =#{tid}")
    public void UpdateTeacherPasswordById(@Param("tid") int tid, @Param("password") String Newpassword);
    @Select("SELECT Teach.sid,name,email FROM Teach LEFT JOIN Student ON Teach.sid = Student.sid WHERE tid =#{tid};")//老师查找学生
    public List<StudentResult> FindMyStudentBytid(@Param("tid") int tid);

}
