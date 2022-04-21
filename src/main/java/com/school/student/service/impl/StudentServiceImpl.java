package com.school.student.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.student.domain.Student;
import com.school.student.repository.StudentRepository;
import com.school.student.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student addNewStudent(Student student) {
		return this.studentRepository.save(student);
	}

	@Override
	public Student updateStudent(Integer id, Student newStudentDetails) {
		Student existingStudent = getStudentById(id);
		existingStudent.setStudentFirstName(newStudentDetails.getStudentFirstName());
		existingStudent.setStudentLastName(newStudentDetails.getStudentLastName());
		existingStudent.setStudentPercentage(newStudentDetails.getStudentPercentage());
		return studentRepository.save(existingStudent);
	}

	@Override
	public Student getStudentById(Integer id) {
		return studentRepository.findById(id).get();
	}

	@Override
	public boolean existsById(Integer id) {
		return studentRepository.existsById(id);
	}

	@Override
	public void deleteStudent(Integer id) {
		studentRepository.deleteById(id);
	}

	@Override
	public List<Student> getPercentageDesc() {
		List<Student> percentageDescList = studentRepository.findAll();
		return percentageDescList.stream().sorted(Comparator.comparingInt(Student::getStudentPercentage).reversed())
				.collect(Collectors.toList());
	}
}
