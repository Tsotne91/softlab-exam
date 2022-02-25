package com.example.softlabexam.service;

import com.example.softlabexam.model.Student;
import com.example.softlabexam.repository.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class SchoolServiceImpl implements SchoolService {

    private final StudentsRepository studentsRepository;

    public Student addStudent(Student student) {
        return studentsRepository.save(student);
    }

    @Transactional
    public Student editStudent(Integer id) {
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid ID");
        }
        var student = studentsRepository
                .findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        studentsRepository.save(student);
        return student;
    }

    @Transactional
    public Student deleteStudent(Integer id) {
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid ID");
        }
        var student = studentsRepository
                .findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        studentsRepository.delete(student);

        return student;
    }

}
