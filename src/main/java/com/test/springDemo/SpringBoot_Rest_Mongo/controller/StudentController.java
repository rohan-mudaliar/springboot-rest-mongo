package com.test.springDemo.SpringBoot_Rest_Mongo.controller;

import com.test.springDemo.SpringBoot_Rest_Mongo.model.Student;
import com.test.springDemo.SpringBoot_Rest_Mongo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/students")
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

	@RequestMapping(value = "/getStudentByID", method = RequestMethod.POST)
	public Student getStudentByID(Integer studentId) {
		return studentRepository.findById(studentId).orElse(new Student(0, "Invalid", 0));

	}

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
