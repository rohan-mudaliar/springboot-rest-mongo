package com.test.springDemo.SpringBoot_Rest_Mongo.dao;

import com.test.springDemo.SpringBoot_Rest_Mongo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl  implements StudentDao{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Student> getAllStudents() {
        return mongoTemplate.findAll(Student.class);
    }
}
