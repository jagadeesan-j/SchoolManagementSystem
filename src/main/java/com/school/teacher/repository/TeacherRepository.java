package com.school.teacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.teacher.domain.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}
