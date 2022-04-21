package com.school.student.controller;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.exception.ResourceNotFoundException;
import com.school.student.domain.Student;
import com.school.student.service.StudentService;

@RestController
@RequestMapping("/school")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/students")
	public ResponseEntity<?> getAllStudents() {
		if (studentService.getAllStudents().isEmpty())
			return new ResponseEntity<>("No records of student found!", HttpStatus.OK);
		else
			return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable(value = "id") Integer id) {
		try {
			return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ResourceNotFoundException("Student by " + id + " ID not found!").getMessage(), HttpStatus.OK);
		}
	}

	@PostMapping("/students/add")
	public ResponseEntity<?> addNewStudent(@RequestBody Student student) {
		try {
			if (studentService.existsById(student.getStudentId()))
				return new ResponseEntity<>(
						"Student by " + student.getStudentId() + " ID exists already! Please provide an unique ID.",
						HttpStatus.OK);
			else
				return new ResponseEntity<>(studentService.addNewStudent(student), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: Check when the exception is thrown.
			return new ResponseEntity<>("Please check the input and format.", HttpStatus.OK);
		}
	}

	@PutMapping("/students/update/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable(value = "id") Integer id,
			@Valid @RequestBody Student student) {
		try {
			return new ResponseEntity<>(studentService.updateStudent(id, student), HttpStatus.OK);
		} catch (NoSuchElementException noSuchElement) {
			return new ResponseEntity<>("Student by " + id + " ID not found!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.OK);
		}
	}

	@DeleteMapping("/students/delete/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") Integer id) {
		try {
			studentService.deleteStudent(id);
			return new ResponseEntity<>("Successfully deleted Student with ID as " + id, HttpStatus.OK);
		} catch (EmptyResultDataAccessException emptyResultData) {
			return new ResponseEntity<>("Student by " + id + " ID not found!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.OK);
		}
	}

	@GetMapping("/students/percentage-in-desc")
	public ResponseEntity<?> getPercentageDesc() {
		if (studentService.getAllStudents().isEmpty())
			return new ResponseEntity<>("Students record is empty!", HttpStatus.OK);
		else
			return new ResponseEntity<>(studentService.getPercentageDesc(), HttpStatus.OK);
	}
}
