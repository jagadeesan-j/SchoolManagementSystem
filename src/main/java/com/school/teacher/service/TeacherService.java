package com.school.teacher.service;

import java.util.List;

import com.school.teacher.domain.Teacher;

public interface TeacherService {

	List<Teacher> getAllTeachers();
	
	Teacher addNewTeacher(Teacher teacher);
	
	Teacher updateTeacher(Integer id, Teacher teacher);
	Teacher getTeacherById(Integer id);
	
	boolean existsById(Integer id);
	
	void deleteTeacher(Integer id);
	
	List<Teacher> getTeacherFirstNameAsc();
}
