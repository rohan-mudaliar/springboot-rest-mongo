package com.test.springDemo.SpringBoot_Rest_Mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Student")
@Data
@AllArgsConstructor(onConstructor_ = {@Autowired} )
public class Student {

    @Id
    private Integer id;
    private String name;
    private Integer section;
}
