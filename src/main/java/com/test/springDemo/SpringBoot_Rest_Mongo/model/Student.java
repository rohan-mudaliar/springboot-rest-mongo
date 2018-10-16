package com.test.springDemo.SpringBoot_Rest_Mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Student")
@Data
@AllArgsConstructor(onConstructor_ = {@Autowired} )
public class Student implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -1041429917402161963L;
	@Id
    private Integer id;
    private String name;
    private Integer section;
}
