package com.school.course.controller;

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

import com.school.course.domain.Course;
import com.school.course.service.CourseService;
import com.school.exception.ResourceNotFoundException;
import com.school.student.domain.Student;
import com.school.student.service.StudentService;

@RestController
@RequestMapping("/school")
public class CourseController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentService studentService;

	@GetMapping("/courses")
	public ResponseEntity<?> getAllCourses() {
		if (courseService.getAllCourses().isEmpty())
			return new ResponseEntity<>("No records of course found!", HttpStatus.OK);
		else
			return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
	}

	@GetMapping("/courses/{id}")
	public ResponseEntity<?> getCourseById(@PathVariable(value = "id") String id) {
		try {
			return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ResourceNotFoundException("Course by " + id + " ID not found!").getMessage(), HttpStatus.OK);
		}
	}

	@PostMapping("/courses/add")
	public ResponseEntity<?> addNewCourse(@RequestBody Course course) {
		try {
			if (courseService.existsById(course.getCourseId()))
				return new ResponseEntity<>(
						"Course by " + course.getCourseId() + " ID exists already! Please provide an unique ID.",
						HttpStatus.OK);
			else
				return new ResponseEntity<>(courseService.addNewCourse(course), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: Check when the exception is thrown.
			return new ResponseEntity<>("Please check the input and format.", HttpStatus.OK);
		}
	}
	
	@PutMapping("/courses/update/{id}")
	public ResponseEntity<?> updateCourse(@PathVariable(value = "id") String id,
			@Valid @RequestBody Course course) {
		try {
			return new ResponseEntity<>(courseService.updateCourse(id, course), HttpStatus.OK);
		} catch (NoSuchElementException noSuchElement) {
			return new ResponseEntity<>("Course by " + id + " ID not found!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/courses/delete/{id}")
	public ResponseEntity<?> deleteCourse(@PathVariable(value = "id") String id) {
		try {
			courseService.deleteCourse(id);
			return new ResponseEntity<>("Successfully deleted Course with ID as " + id, HttpStatus.OK);
		} catch (EmptyResultDataAccessException emptyResultData) {
			return new ResponseEntity<>("Course by " + id + " ID not found!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.OK);
		}
	}
	
	@GetMapping("/courses/coursename-in-asc")
	public ResponseEntity<?> getCourseNameAsc() {
		if (courseService.getAllCourses().isEmpty())
			return new ResponseEntity<>("Courses record is empty!", HttpStatus.OK);
		else
			return new ResponseEntity<>(courseService.getCourseNameAsc(), HttpStatus.OK);
	}
	
	@PutMapping("/courses/{course_id}/addStudent/{student_id}")
	public ResponseEntity<?> enrollStudentToCourse(@PathVariable String courseId, @PathVariable Integer studentId) {
		Course course = courseService.getCourseById(courseId);
		Student student = studentService.getStudentById(studentId);
		course.enrollStudent(student);
		courseService.addNewCourse(course);
		return new ResponseEntity<>("Student with ID " + studentId + " successfully enrolled to course with ID " + courseId, HttpStatus.OK);
	}
}
