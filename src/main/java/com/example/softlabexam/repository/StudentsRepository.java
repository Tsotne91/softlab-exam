package com.example.softlabexam.repository;

import com.example.softlabexam.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StudentsRepository extends JpaRepository<Student, Integer>,
        JpaSpecificationExecutor<Student> {
//    @Query("select s from Student s where s.firstName = :name or  s.lastName = :name")
//    List<Student> findStudent(String name);

}