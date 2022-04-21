package com.school.teacher.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.teacher.domain.Teacher;
import com.school.teacher.repository.TeacherRepository;
import com.school.teacher.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		super();
		this.teacherRepository = teacherRepository;
	}

	@Override
	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public Teacher addNewTeacher(Teacher teacher) {
		return this.teacherRepository.save(teacher);
	}

	@Override
	public Teacher updateTeacher(Integer id, Teacher newTeacherDetails) {
		Teacher existingTeacher = getTeacherById(id);
		existingTeacher.setTeacherFirstName(newTeacherDetails.getTeacherFirstName());
		existingTeacher.setTeacherLastName(newTeacherDetails.getTeacherLastName());
		existingTeacher.setTeacherEmail(newTeacherDetails.getTeacherEmail());
		return teacherRepository.save(existingTeacher);
	}

	@Override
	public Teacher getTeacherById(Integer id) {
		return teacherRepository.findById(id).get();
	}

	@Override
	public boolean existsById(Integer id) {
		return teacherRepository.existsById(id);
	}

	@Override
	public void deleteTeacher(Integer id) {
		teacherRepository.deleteById(id);
	}

	@Override
	public List<Teacher> getTeacherFirstNameAsc() {
		List<Teacher> teacherFirstNameAscList = teacherRepository.findAll();
		return teacherFirstNameAscList.stream().sorted(Comparator.comparing(Teacher::getTeacherFirstName))
				.collect(Collectors.toList());
	}
}
