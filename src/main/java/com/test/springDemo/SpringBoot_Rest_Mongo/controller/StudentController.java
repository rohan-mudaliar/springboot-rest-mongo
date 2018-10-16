package com.test.springDemo.SpringBoot_Rest_Mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.springDemo.SpringBoot_Rest_Mongo.model.Student;
import com.test.springDemo.SpringBoot_Rest_Mongo.repository.StudentRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/rest/students")
@Slf4j
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class StudentController {

	private StudentRepository studentRepository;

	@RequestMapping(value = "/getAllStudents", method = RequestMethod.GET)
	public List<Student> getAllStudents() {
		return studentRepository.findAll();

	}

	@RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
	public void saveStudent(Student student) {
		studentRepository.save(student);

	}

	/**
	 * @Cacheable annotation is used to put something into cache
	 * @param studentId
	 * @return
	 */
	@Cacheable(value = "students", key = "#studentId", unless = "#result.id<2")
	@RequestMapping(value = "/getStudentByID", method = RequestMethod.POST)
	public Student getStudentByID(Integer studentId) {
		log.info("Getting user with ID {}.", studentId);
		return studentRepository.findById(studentId).orElse(new Student(0, "Invalid", 0));

	}

	/**
	 * CachePut updates an already existing key in cache
	 * @param student
	 */
	@CachePut(value = "students", key = "#student.id")
	@RequestMapping(value = "/updateStudentInfo", method = RequestMethod.POST)
	public void updateStudentInfo(Student student) {
		studentRepository.save(student);

	}

	/**
	 * @CacheEvict is used to delete a value from cache
	 * @param studentId
	 */
	@CacheEvict(value = "students", allEntries = true)
	@RequestMapping(value = "/deleteStudent", method = RequestMethod.POST)
	public void deleteStudent(Integer studentId) {
		Student studToDelete = (studentRepository.findById(studentId)) != null
				? studentRepository.findById(studentId).get()
				: null;
		if (studToDelete != null) {
			studentRepository.delete(studToDelete);
		}
	}
}
