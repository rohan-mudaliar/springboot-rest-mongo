package com.test.springDemo.SpringBoot_Rest_Mongo.model;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class GradeSheetBean implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 6907841603347456435L;
	Map<String, Integer> Subjects;
	String semester;
	int port;
}
