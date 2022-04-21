package com.school.teacher.controller;

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
import com.school.teacher.domain.Teacher;
import com.school.teacher.service.TeacherService;

@RestController
@RequestMapping("/school")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@GetMapping("/teachers")
	public ResponseEntity<?> getAllTeachers() {
		if (teacherService.getAllTeachers().isEmpty())
			return new ResponseEntity<>("No records of teacher found!", HttpStatus.OK);
		else
			return new ResponseEntity<>(teacherService.getAllTeachers(), HttpStatus.OK);
	}

	@GetMapping("/teachers/{id}")
	public ResponseEntity<?> getTeacherById(@PathVariable(value = "id") Integer id) {
		try {
			return new ResponseEntity<>(teacherService.getTeacherById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ResourceNotFoundException("Teacher by " + id + " ID not found!").getMessage(), HttpStatus.OK);
		}
	}

	@PostMapping("/teachers/add")
	public ResponseEntity<?> addNewTeacher(@RequestBody Teacher teacher) {
		try {
			if (teacherService.existsById(teacher.getTeacherId()))
				return new ResponseEntity<>(
						"Teacher by " + teacher.getTeacherId() + " ID exists already! Please provide an unique ID.",
						HttpStatus.OK);
			else
				return new ResponseEntity<>(teacherService.addNewTeacher(teacher), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: Check when the exception is thrown.
			return new ResponseEntity<>("Please check the input and format.", HttpStatus.OK);
		}
	}

	@PutMapping("/teachers/update/{id}")
	public ResponseEntity<?> updateTeacher(@PathVariable(value = "id") Integer id,
			@Valid @RequestBody Teacher teacher) {
		try {
			return new ResponseEntity<>(teacherService.updateTeacher(id, teacher), HttpStatus.OK);
		} catch (NoSuchElementException noSuchElement) {
			return new ResponseEntity<>("Teacher by " + id + " ID not found!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.OK);
		}
	}

	@DeleteMapping("/teachers/delete/{id}")
	public ResponseEntity<?> deleteTeacher(@PathVariable(value = "id") Integer id) {
		try {
			teacherService.deleteTeacher(id);
			return new ResponseEntity<>("Successfully deleted Teacher with ID as " + id, HttpStatus.OK);
		} catch (EmptyResultDataAccessException emptyResultData) {
			return new ResponseEntity<>("Teacher by " + id + " ID not found!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.OK);
		}

	}

	@GetMapping("/teachers/firstname-in-asc")
	public ResponseEntity<?> getTeacherFirstNameAsc() {
		if (teacherService.getAllTeachers().isEmpty())
			return new ResponseEntity<>("Teachers record is empty!", HttpStatus.OK);
		else
			return new ResponseEntity<>(teacherService.getTeacherFirstNameAsc(), HttpStatus.OK);
	}
}
