package com.check.controller;


import com.alibaba.fastjson.JSONObject;
import com.check.dao.ClassRoomRepository;
import com.check.dao.CourseRepository;
import com.check.dao.TeacherRepository;
import com.check.entity.Course;
import com.check.entity.Teacher;
import com.check.util.MjStringUtil;
import com.check.util.RespMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.io.IOException;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private ClassRoomRepository classRoomRepository;
	
	//添加
	@RequestMapping(value="/add")
	protected JSONObject add(@RequestBody JSONObject jsonObject) throws IOException {
		JSONObject jo ;
		int ID = jsonObject.getIntValue("courseID");
		String name = jsonObject.getString("courseName");
		int period = jsonObject.getIntValue("period");
		int tno = jsonObject.getIntValue("tno");
		String tname = jsonObject.getString("tname");

		if(MjStringUtil.isEmpty(jsonObject)){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		if(teacherRepository.findByTno(tno)==null){
			return RespMsgUtil.getFailResponseJoWithErrMsg("该老师不存在，请重新选择工号");
		}
		Course course = new Course(ID,name,period,tno,tname);
		if(courseRepository.findByCourseID(course.getCourseID())!=null || courseRepository.findByCourseName(course.getCourseName())!=null){
			return RespMsgUtil.getFailResponseJoErrInsertExist();
		}
		
		try {
		Teacher teacher = teacherRepository.findByTno(tno);
		teacher.getCourses().add(course);
		course = courseRepository.save(course);
		teacherRepository.save(teacher);
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
	protected JSONObject getByName(@RequestBody JSONObject jsonObject) throws IOException {
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


	//更新
	@RequestMapping("/updateName")
	protected JSONObject updateNameByID(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("newName")) || MjStringUtil.isEmpty(jsonObject.getIntValue("ID"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		
		Course course;
		if(courseRepository.findByCourseID(jsonObject.getIntValue("ID"))==null){
			return RespMsgUtil.getFailResponseJoErrUpdateNotExist();
		}
		try {
			courseRepository.updateNameByID(jsonObject.getString("newName"), jsonObject.getIntValue("ID"));
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
		if(courseRepository.findByCourseID(jsonObject.getIntValue("ID"))==null){
			return RespMsgUtil.getFailResponseJoErrUpdateNotExist();
		}
		try {
			courseRepository.updatePeriodByID(jsonObject.getIntValue("newPeriod"), jsonObject.getIntValue("ID"));
			course = courseRepository.findByCourseID(jsonObject.getIntValue("ID"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		return RespMsgUtil.getSuccessResponseJoWithData(course);
	}


	//删除课程
	@RequestMapping("/deleteByID")
	protected JSONObject deleteByID(@RequestBody JSONObject jsonObject) throws IOException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("ID"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		Course course;
		int ID = jsonObject.getIntValue("ID");
		try {
			course = courseRepository.findByCourseID(ID);
			if(course==null){
				return RespMsgUtil.getFailResponseJoErrDeleteNotExist();
			}
			Teacher teacher = teacherRepository.findByTno(course.getTno());
			teacher.getCourses().remove(course);
			teacherRepository.save(teacher);
			course.getClassrooms().clear();
			classRoomRepository.deleteByCno(ID);
			courseRepository.deleteByCourseID(ID);
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}

			return  RespMsgUtil.getSuccessResponseJo();

	}

}
