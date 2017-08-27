package com.check.controller;



import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.check.dao.CourseRepository;
import com.check.entity.Course;
import com.check.util.MjJSONUtil;
import com.check.util.MjStringUtil;
import com.check.util.RespMsgUtil;





@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseRepository courseRepository;
	
	//添加
	@RequestMapping(value="/add")
	protected JSONObject add(@RequestBody JSONObject jsonObject) throws IOException {
		JSONObject jo = null;
		int ID = jsonObject.getIntValue("courseID");
		String name = jsonObject.getString("courseName");
		int period = jsonObject.getIntValue("period");
		int capacity = jsonObject.getIntValue("capacity");
		String place = jsonObject.getString("place");
		String time = jsonObject.getString("time");
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(ID) || MjStringUtil.isEmpty(name) || MjStringUtil.isEmpty(period) ||
				MjStringUtil.isEmpty(capacity) || MjStringUtil.isEmpty(place) || MjStringUtil.isEmpty(time)){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		
		Course course = MjJSONUtil.jsonToBean(jsonObject,Course.class);
		if(courseRepository.findByCourseID(course.getCourseID())!=null){
			return RespMsgUtil.getFailResponseJoErrInsertExist();
		}
		
		try {
		course = courseRepository.save(course);
		jo = RespMsgUtil.getSuccessResponseJoWithData(course);
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}

		return jo;
	}
	
	
	//查询
	@RequestMapping("/getByID")
	protected JSONObject getByID(@RequestBody JSONObject jsonObject) throws IOException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("ID"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		Course course;
		try {
			course = courseRepository.findByCourseID(jsonObject.getIntValue("ID"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		if(course == null){
			return RespMsgUtil.getFailResponseJoErrGetNotExist();
		}else{
			return  RespMsgUtil.getSuccessResponseJoWithData(course);
		}
	}
	
	@RequestMapping("/getByName")
	protected JSONObject getByCourseName(@RequestBody JSONObject jsonObject) throws IOException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("name"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		List<Course> courses;
		try {
			courses = courseRepository.findByCourseName(jsonObject.getString("name"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		if(courses == null){
			return RespMsgUtil.getFailResponseJoErrGetNotExist();
		}else{
			return RespMsgUtil.getSuccessResponseJoWithData(courses);
		}
	}
	

	
	@RequestMapping("/getAll")
	protected JSONObject  getAll() throws IOException{
		
		List<Course> courses;
		try {
			courses = courseRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		return	add(RespMsgUtil.getFailResponseJoErrHibernate());
		
		}
		
		return RespMsgUtil.getSuccessResponseJoWithData(courses);
	}
	

	@RequestMapping("/count")
	protected JSONObject count() {
		JSONObject jsonObject = new JSONObject();
		try {
			long count = courseRepository.count();
			jsonObject.put("count",count);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		return RespMsgUtil.getSuccessResponseJoWithData(jsonObject);
	}
	
	//删除
	@RequestMapping("/deleteByID")
	protected JSONObject deleteByID(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("ID"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}else if(courseRepository.findByCourseID(jsonObject.getIntValue("ID")) == null){
			return RespMsgUtil.getFailResponseJoErrDeleteNotExist();
		}
		
		try {
				courseRepository.deleteByCourseID(jsonObject.getIntValue("ID"));
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
		return RespMsgUtil.getSuccessResponseJo();
	}
	
	
	@RequestMapping("deleteAll")
	protected JSONObject deleteAll() {
		try {
			courseRepository.deleteAll();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		return RespMsgUtil.getSuccessResponseJo();
	}
	
	
	//更新
	@RequestMapping("/updateName")
	protected JSONObject updateNameByID(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("newName")) || MjStringUtil.isEmpty(jsonObject.getIntValue("ID"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		
		Course course;
		try {
			courseRepository.updateNameByID(jsonObject.getString("newName"), jsonObject.getIntValue("ID"));
			course = courseRepository.findByCourseID(jsonObject.getIntValue("ID"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		return RespMsgUtil.getSuccessResponseJoWithData(course);
	}
	
	@RequestMapping("/updatePlace")
	protected JSONObject updatePlaceByID(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("newPlace")) || MjStringUtil.isEmpty(jsonObject.getIntValue("ID"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		
		Course course;
		try {
			courseRepository.updatePlaceByID(jsonObject.getString("newPlace"), jsonObject.getIntValue("ID"));
			course = courseRepository.findByCourseID(jsonObject.getIntValue("ID"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		return RespMsgUtil.getSuccessResponseJoWithData(course);
	}
	
	@RequestMapping("/updateTime")
	protected JSONObject updateTimeByID(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("newTime")) || MjStringUtil.isEmpty(jsonObject.getIntValue("ID"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		
		Course course;
		try {
			courseRepository.updateTimeByID(jsonObject.getString("newTime"), jsonObject.getIntValue("ID"));
			course = courseRepository.findByCourseID(jsonObject.getIntValue("ID"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		return RespMsgUtil.getSuccessResponseJoWithData(course);
	}
	
	@RequestMapping("/updateCapacity")
	protected JSONObject updateCapacityByID(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("newCapacity")) || MjStringUtil.isEmpty(jsonObject.getIntValue("ID"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		
		Course course;
		try {
			courseRepository.updateCapacityByID(jsonObject.getIntValue("newCapacity"), jsonObject.getIntValue("ID"));
			course = courseRepository.findByCourseID(jsonObject.getIntValue("ID"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		return RespMsgUtil.getSuccessResponseJoWithData(course);
	}

	@RequestMapping("/updatePeriod")
	protected JSONObject updatePeriodByID(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("newPeriod")) || MjStringUtil.isEmpty(jsonObject.getIntValue("ID"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		
		Course course;
		try {
			courseRepository.updatePeriodByID(jsonObject.getIntValue("newPeriod"), jsonObject.getIntValue("ID"));
			course = courseRepository.findByCourseID(jsonObject.getIntValue("ID"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		return RespMsgUtil.getSuccessResponseJoWithData(course);
	}
	
}
