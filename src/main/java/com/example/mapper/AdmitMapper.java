package com.example.mapper;

import com.example.entity.Admit;
import com.example.entity.Student;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AdmitMapper {
    @Select("select * from admit where id=#{id}")
    public Admit getAdimitById(@Param("id") int id);
    @Insert("INSERT INTO `firstproject`.`Teach`(`tid`, `sid`) VALUES (#{tid}, #{sid}")
    public void TeacherAddStudent(@Param("tid") int tid, @Param("sid")int sid);

    @Delete("DELETE FROM Teach WHERE sid=#{sid}")
    void TeacherdeleteStudentBySid(int sid);
}
