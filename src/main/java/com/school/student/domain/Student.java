package com.school.student.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.security.auth.Subject;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Students")
public class Student {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "StudentId")
	private Integer studentId;

	@Column(name = "FirstName")
	private String studentFirstName;

	@Column(name = "LastName")
	private String studentLastName;

	@Column(name = "Percentage")
	private Integer studentPercentage;

	@ManyToMany(mappedBy = "enrolledStudents")
	@JsonIgnore
	private Set<Subject> subjectsEnrolled = new HashSet<>();

	public Student() {
		super();
	}

	public Student(Integer studentId, String studentFirstName, String studentLastName, Integer studentPercentage) {
		super();
		this.studentId = studentId;
		this.studentFirstName = studentFirstName;
		this.studentLastName = studentLastName;
		this.studentPercentage = studentPercentage;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public Integer getStudentPercentage() {
		return studentPercentage;
	}

	public void setStudentPercentage(Integer studentPercentage) {
		this.studentPercentage = studentPercentage;
	}

	public Set<Subject> getSubjectsEnrolled() {
		return subjectsEnrolled;
	}
}
