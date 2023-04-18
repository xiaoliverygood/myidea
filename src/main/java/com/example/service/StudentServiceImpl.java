package com.example.service;

import com.example.entity.TeacherResult;
import com.example.mapper.AdmitMapper;
import com.example.mapper.StudentMapper;
import com.example.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public List<String> FindMylesson(int sid) {
        List<TeacherResult> MyTeacheList=studentMapper.getTeacherBysid(sid);
        List<String> listMyLesson = new ArrayList<String>();
        int i=0;
        while(i<MyTeacheList.size()){
            listMyLesson.add(MyTeacheList.get(i).getLesson());
            i++;
        }

        return listMyLesson;
    }

    public List<TeacherResult> FindeTeacher(int sid){
        return studentMapper.getTeacherBysid(sid);
    }

}
