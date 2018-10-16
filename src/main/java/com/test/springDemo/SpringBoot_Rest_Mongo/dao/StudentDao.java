package com.test.springDemo.SpringBoot_Rest_Mongo.dao;

import com.test.springDemo.SpringBoot_Rest_Mongo.model.Student;
import java.util.List;

public interface StudentDao {

    public  List<Student> getAllStudents();
}
