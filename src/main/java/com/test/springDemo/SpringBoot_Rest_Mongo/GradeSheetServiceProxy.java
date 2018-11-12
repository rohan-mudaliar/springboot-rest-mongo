package com.test.springDemo.SpringBoot_Rest_Mongo;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.springDemo.SpringBoot_Rest_Mongo.model.GradeSheetBean;


@FeignClient(name="grade-sheet-service")
@RibbonClient(name="grade-sheet-service")
public interface GradeSheetServiceProxy {
	
	@RequestMapping(value = "/getStudentGradeInfo/{semester}", method = RequestMethod.POST)
	public GradeSheetBean getStudentGradeInfo(@PathVariable("semester") String semester) ;

}
