package com.school.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.course.domain.Course;

public interface CourseRepository extends JpaRepository<Course, String>{

}
