package com.example.mapper;

import com.example.entity.Student;
import com.example.entity.TeacherResult;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Insert("INSERT INTO `firstproject`.`Student`(`sid`, `name`, `email`, `password`) VALUES (#{Student.sid}, #{Student.name}, #{Student.email},#{Student.password})")
    void insertStudent(@Param("Student")Student student);
//注意，不需要返回值，不然会出现报错的情况，但是数据库里面是增加了
    @Select("select * from Student where sid=#{sid}")
    public Student getStudentById(int sid);
    @Select("select * from Student where email=#{email}")
    public Student getStudentByEmile(String email);
    @Delete("DELETE FROM Student WHERE sid=#{sid}")
    void deleteStudentBySid(int sid);
    @Update("UPDATE Student SET name = #{name} WHERE sid =#{sid}")
    public void UpdateStudentNameById(@Param("sid") int sid, @Param("name") String Newname);
    @Update("UPDATE Student SET password = #{password} WHERE sid =#{sid}")
    public void UpdateStudentPasswordById(@Param("sid") int sid, @Param("password") String Newpassword);
//    @Select("SELECT Teach.tid,name,email,lesson FROM Teach LEFT JOIN Teacher ON Teach.tid = Teacher.tid WHERE sid = #{sid};")
//    public TeacherResult getTeacherBysid(int sid);
@Select(" select Teach.tid,name,email,lesson from Teach left join Teacher on Teacher.tid=Teach.tid where sid=1;")
public List<TeacherResult> getTeacherBysid(int sid);
}
