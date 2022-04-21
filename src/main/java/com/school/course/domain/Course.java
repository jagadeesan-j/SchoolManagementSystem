package com.school.course.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.school.student.domain.Student;

@Entity
@Table(name = "Courses")
public class Course {

	@Id
	@Column(name = "courseId")
	private String courseId;

	@Column(name = "CourseName")
	private String courseName;

	@ManyToMany
	@JoinTable(name = "subject_students_enrolled", 
		joinColumns = @JoinColumn(name = "subject_id"), 
		inverseJoinColumns = @JoinColumn(name = "student_id"))
	private Set<Student> enrolledStudents = new HashSet<>();

	public Course() {
		super();
	}

	public Course(String courseId, String courseName) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
	}
	
	public void enrollStudent(Student student) {
		enrolledStudents.add(student);
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Set<Student> getEnrolledStudents() {
		return enrolledStudents;
	}
}
