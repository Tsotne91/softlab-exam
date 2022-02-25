package com.example.softlabexam.service;

import com.example.softlabexam.model.Student;


public interface SchoolService {
    Student addStudent(Student student);

    Student editStudent(Integer id, Student student);

    Student deleteStudent(Integer id);

}
