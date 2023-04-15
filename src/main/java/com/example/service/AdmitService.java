package com.example.service;

public interface AdmitService {
    public void deleteStudent(int sId);
    public void deleteTeacher(int tId);
    public void TeacherAddStudent(int sId, int tId);

    public void TeacherDeleteStudent(int sId);


}
