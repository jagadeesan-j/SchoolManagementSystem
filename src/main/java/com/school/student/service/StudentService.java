package com.school.student.service;

import java.util.List;

import com.school.student.domain.Student;

public interface StudentService {
	
	List<Student> getAllStudents();
	
	Student addNewStudent(Student student);
	
	Student updateStudent(Integer id, Student student);
	Student getStudentById(Integer id);
	
	boolean existsById(Integer id);
	
	void deleteStudent(Integer id);
	
	List<Student> getPercentageDesc();

}
