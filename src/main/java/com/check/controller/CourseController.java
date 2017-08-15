package com.check.controller;



import java.sql.Time;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.check.dao.CourseRepository;
import com.check.entity.Course;
import com.check.entity.Teacher;
import com.check.util.StringToTime;




/**
 * Created by yancychan on 17-6-28.
 */
@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseRepository courseRepository;
	
	//添加
	@RequestMapping(value="/add")
	public Course add(@RequestBody Course course) {
		return courseRepository.save(course);
	}
	
	
	//查询
	@RequestMapping("/getByCourseID")
	public Course getBySno(int ID) {
		return courseRepository.findByCourseID(ID);
	}
	
	@RequestMapping("/getByCourseName")
	public Course getByCourseName(String name) {
		return courseRepository.findByCourseName(name);
	}
	
	
	@RequestMapping("/getAll")
	public List<Course> getAll(){
		return courseRepository.findAll();
	}
	
	@RequestMapping("/count")
	public Long count() {
		return courseRepository.count();
	}
	
	//更新
	@RequestMapping("/updateNameByID")
	public String updateNameByID(String newName,int ID) {
		courseRepository.updateNameByID(newName, ID);
		return "更新成功"; 
	}
	
	@RequestMapping("/updateIDByName")
	public String updateIDByName(int ID,String Name) {
		courseRepository.updateIDByName(ID, Name);
		return "更新成功"; 
	}
	
	@RequestMapping("/updateStartTime")
	public String updateStartTimeByCourseID(String time,int ID) throws ParseException {
		
		courseRepository.updateStartTimeByCourseID(StringToTime.Convert(time), ID);
		return "更新成功"; 
	}
	
	@RequestMapping("/updateEndTime")
	public String updateEndTimeByCourseID(String time,int ID) throws ParseException {
		courseRepository.updateEndTimeByCourseID(StringToTime.Convert(time), ID);
		return "更新成功"; 
	}
	
	@RequestMapping("/updateWeek")
	public String updateWeekByCourseID(String week,int ID) {
		courseRepository.updateWeekByCourseID(week, ID);
		return "更新成功"; 
	}
	
	@RequestMapping("/updatePlace")
	public String updatePlaceByCourseID(String newPlace,int ID) {
		courseRepository.updatePlaceByCourseID(newPlace, ID);
		return "更新成功"; 
	}
	
		//删除
	@RequestMapping("/deleteByCourseName")
	public String deleteByCourseName(String name) {
		courseRepository.deleteByCourseName(name);
		return "删除成功";
	}
	
	@RequestMapping("/deleteByCourseID")
	public String deleteByCourseID(int ID) {
		Course course = courseRepository.findByCourseID(ID);
		courseRepository.delete(course);
		return "删除成功";
	}
	
	@RequestMapping("deleteAll")
	public String deleteAll() {
		courseRepository.deleteAll();
		return "删除成功";
	}
	
	
	
}
