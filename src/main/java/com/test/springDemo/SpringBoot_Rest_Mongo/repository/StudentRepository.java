package com.test.springDemo.SpringBoot_Rest_Mongo.repository;

import com.test.springDemo.SpringBoot_Rest_Mongo.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student,Integer> {
}
