package com.school.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.student.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
