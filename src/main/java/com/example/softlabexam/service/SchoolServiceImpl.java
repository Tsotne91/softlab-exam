package com.example.softlabexam.service;

import com.example.softlabexam.model.Student;
import com.example.softlabexam.repository.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SchoolServiceImpl implements SchoolService {

    private final StudentsRepository studentsRepository;

    public Student addStudent(Student student) {
        return studentsRepository.save(student);
    }

    @Transactional
    public Student editStudent(Integer id, Student student) {
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid ID");
        }
        var newStudent = studentsRepository
                .findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        newStudent.setFirstName(student.getFirstName());
        newStudent.setLastName(student.getLastName());
        newStudent.setEmail(student.getEmail());
        newStudent.setBirthDate(student.getBirthDate());
        newStudent.setPersonalNumber(student.getPersonalNumber());
        studentsRepository.save(newStudent);
        return newStudent;
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

    public List<Student> searchStudents(StudentSearch params) {
        return studentsRepository.findAll(((root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (StringUtils.isNotEmpty(params.getLastName())) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(root.get("lastName"), '%' + params.getLastName() + '%'));
            }
            if (StringUtils.isNotEmpty(params.getFirstName())) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(root.get("firstName"), '%' + params.getFirstName() + '%'));
            }
            return predicate;
        }));
    }
}
