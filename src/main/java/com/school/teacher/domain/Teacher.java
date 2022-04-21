package com.school.teacher.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Teachers")
public class Teacher {

	@Id
	@Column(name = "TeacherId")
	private Integer teacherId;
	
	@Column(name = "FirstName")
	private String teacherFirstName;
	
	@Column(name = "LastName")
	private String teacherLastName;
	
	@Column(name = "Email")
	private String teacherEmail;

	public Integer getTeacherId() {
		return teacherId;
	}
	
	public Teacher() {
		super();
	}

	public Teacher(Integer teacherId, String teacherFirstName, String teacherLastName, String teacherEmail) {
		super();
		this.teacherId = teacherId;
		this.teacherFirstName = teacherFirstName;
		this.teacherLastName = teacherLastName;
		this.teacherEmail = teacherEmail;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherFirstName() {
		return teacherFirstName;
	}

	public void setTeacherFirstName(String teacherFirstName) {
		this.teacherFirstName = teacherFirstName;
	}

	public String getTeacherLastName() {
		return teacherLastName;
	}

	public void setTeacherLastName(String teacherLastName) {
		this.teacherLastName = teacherLastName;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}
}
