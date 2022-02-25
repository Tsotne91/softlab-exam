package com.example.softlabexam.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "studentIdGenerator",
        sequenceName = "Students_id_seq",
        allocationSize = 1)
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "studentIdGenerator")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "personal_no")
    private Integer personalNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "birth_date")
    private Date birthDate;
}
