package com.example.softlabexam.repository;

import com.example.softlabexam.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentsRepository extends JpaRepository<Student, Integer> {
    @Query("select s from Student s")
    List<Student> findStudent(String name);
}