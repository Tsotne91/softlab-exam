package com.example.softlabexam.service;

import com.example.softlabexam.model.Student;

import java.util.List;

public interface SchoolService {
    Student addStudent(Student student);

    Student editStudent(Integer id);

    Student deleteStudent(Integer id);
//    List<Student> find(String name);

}
