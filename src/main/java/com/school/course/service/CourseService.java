package com.school.course.service;

import java.util.List;

import com.school.course.domain.Course;

public interface CourseService {

	List<Course> getAllCourses();
	
	Course addNewCourse(Course course);
	
	Course updateCourse(String id, Course course);
	Course getCourseById(String id);
	
	boolean existsById(String id);
	
	void deleteCourse(String id);
	
	List<Course> getCourseNameAsc();
}
