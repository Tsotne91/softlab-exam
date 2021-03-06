package com.example.softlabexam.controller;


import com.example.softlabexam.model.Student;
import com.example.softlabexam.repository.StudentsRepository;
import com.example.softlabexam.service.SchoolService;
import com.example.softlabexam.service.StudentSearch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentsController {
    //private final StudentsRepository studentsRepository;
    private final SchoolService schoolService;

    public StudentsController(StudentsRepository studentsRepository,
                              SchoolService schoolService) {
        //   this.studentsRepository = studentsRepository;
        this.schoolService = schoolService;
    }

    // list all Students
    @GetMapping
    public List<Student> searchStudents(StudentSearch params) {
        return schoolService.searchStudents(params);
    }

    //find student
//    @GetMapping
//    @RequestMapping("/search")
//    public List<Student> findStudents(@RequestBody StudentSearch params){
//        return schoolService.findStudents(params);
//    }

    //add Student
    @PostMapping
    public ResponseEntity<Student> addNewStudent(@RequestBody Student student) {
        Student newStudent = schoolService.addStudent(student);
        return ResponseEntity.status(201).body(newStudent);
    }

    //edit Student
    @PutMapping("edit-student/{id}")
    public ResponseEntity<Student> editStudent(@PathVariable Integer id,
                                               @RequestBody Student student) {
        try {
            var studentEdited = schoolService.editStudent(id, student);
            return ResponseEntity.ok(studentEdited);
        } catch (HttpClientErrorException ignore) {
            return ResponseEntity.status(ignore.getStatusCode()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    //delete student
    @PostMapping("delete-student/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Integer id) {
        try {
            var studentDeleted = schoolService.deleteStudent(id);
            return ResponseEntity.ok(studentDeleted);
        } catch (HttpClientErrorException ignore) {
            return ResponseEntity.status(ignore.getStatusCode()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}