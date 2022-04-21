package com.school.course.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.course.domain.Course;
import com.school.course.repository.CourseRepository;
import com.school.course.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public CourseServiceImpl(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}

	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course addNewCourse(Course course) {
		return this.courseRepository.save(course);
	}

	@Override
	public Course updateCourse(String id, Course newCourse) {
		Course existingCourse = getCourseById(id);
		existingCourse.setCourseId(newCourse.getCourseId());
		existingCourse.setCourseName(newCourse.getCourseName());
		return courseRepository.save(existingCourse);
	}

	@Override
	public Course getCourseById(String id) {
		return courseRepository.findById(id).get();
	}

	@Override
	public boolean existsById(String id) {
		return courseRepository.existsById(id);
	}

	@Override
	public void deleteCourse(String id) {
		courseRepository.deleteById(id);
	}

	@Override
	public List<Course> getCourseNameAsc() {
		List<Course> courseNameAscList = courseRepository.findAll();
		return courseNameAscList.stream().sorted(Comparator.comparing(Course::getCourseName))
				.collect(Collectors.toList());
	}
}
