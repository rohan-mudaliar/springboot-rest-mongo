package com.test.springDemo.SpringBoot_Rest_Mongo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.test.springDemo.SpringBoot_Rest_Mongo.GradeSheetServiceProxy;
import com.test.springDemo.SpringBoot_Rest_Mongo.model.GradeSheetBean;
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
	private GradeSheetServiceProxy gradeSheetServiceProxy;

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
	
	@RequestMapping(value = "/getStudentGradeSheet/{semester}", method = RequestMethod.POST)
	@HystrixCommand(fallbackMethod="fallbackRetriveConfig")
	public GradeSheetBean getStudentGradeSheet(@PathVariable("semester") String semester) {
		log.info("StudentController:getStudentGradeSheet");
		return gradeSheetServiceProxy.getStudentGradeInfo(semester);
	}
	
	public GradeSheetBean fallbackRetriveConfig() {
		return new GradeSheetBean();
	}
	
}
