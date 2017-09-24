package com.check.controller;

import com.alibaba.fastjson.JSONObject;
import com.check.dao.ClassRoomRepository;
import com.check.dao.CourseRepository;
import com.check.entity.ClassRoom;
import com.check.entity.Course;
import com.check.util.MjStringUtil;
import com.check.util.RespMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Transactional
@RequestMapping("/classroom")
public class ClassRoomController {
	
	@Autowired
	private ClassRoomRepository classRoomRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	//添加
	@RequestMapping(value="/add")
	protected JSONObject add(@RequestBody JSONObject jsonObject) throws IOException {
		JSONObject jo ;
		int cno = jsonObject.getIntValue("cno");
		String cname = jsonObject.getString("cname");
		String place = jsonObject.getString("place");
		String time = jsonObject.getString("time");
		int capacity = jsonObject.getIntValue("capacity");
		int number = jsonObject.getIntValue("number");
		if(MjStringUtil.isEmpty(jsonObject)){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		ClassRoom classRoom = new ClassRoom(cno,cname,place,time,capacity,number);
		if(classRoomRepository.findByCnoAndNumber(cno, number)!=null){
			return RespMsgUtil.getFailResponseJoErrInsertExist();
		}
		try {
			Course course = courseRepository.findByCourseID(cno);
			course.getClassrooms().add(classRoom);
			classRoom = classRoomRepository.save(classRoom);
			courseRepository.save(course);
			jo = RespMsgUtil.getSuccessResponseJoWithData(classRoom);
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		return jo;
	}

	
	@RequestMapping("/getByCno")
	protected JSONObject getByCno(@RequestBody JSONObject jsonObject) throws IOException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("cno"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		List<ClassRoom> classRooms;
		try {
			classRooms = classRoomRepository.findByCno(jsonObject.getIntValue("cno"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		if(classRooms == null){
			return RespMsgUtil.getFailResponseJoErrGetNotExist();
		}else{
			return  RespMsgUtil.getSuccessResponseJoWithData(classRooms);
		}
	}

	@RequestMapping("/getByCnoAndNumber")
	protected JSONObject getByCnoAndNumber(@RequestBody JSONObject jsonObject) throws IOException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("cno")) || MjStringUtil.isEmpty(jsonObject.getIntValue("number"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		ClassRoom classRoom;
		try {
			classRoom = classRoomRepository.findByCnoAndNumber(jsonObject.getIntValue("cno"),jsonObject.getIntValue("number"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		if(classRoom == null){
			return RespMsgUtil.getFailResponseJoErrGetNotExist();
		}else{
			return  RespMsgUtil.getSuccessResponseJoWithData(classRoom);
		}
	}


	@RequestMapping("/updatePlace")
	protected JSONObject updatePlace(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("newPlace")) || MjStringUtil.isEmpty(jsonObject.getIntValue("cno"))
		|| MjStringUtil.isEmpty(jsonObject.getIntValue("number"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}

		ClassRoom classRoom;
		try {
			classRoomRepository.updatePlaceByID(jsonObject.getString("newPlace"), jsonObject.getIntValue("cno"), jsonObject.getIntValue("number"));
			classRoom = classRoomRepository.findByCnoAndNumber(jsonObject.getIntValue("cno"),jsonObject.getIntValue("number"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		return RespMsgUtil.getSuccessResponseJoWithData(classRoom);
	}

	@RequestMapping("/updateTime")
	protected JSONObject updateTime(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("newTime")) || MjStringUtil.isEmpty(jsonObject.getIntValue("cno"))
				|| MjStringUtil.isEmpty(jsonObject.getIntValue("number"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}

		ClassRoom classRoom;
		try {
			classRoomRepository.updateTimeByID(jsonObject.getString("newTime"), jsonObject.getIntValue("cno"), jsonObject.getIntValue("number"));
			classRoom = classRoomRepository.findByCnoAndNumber(jsonObject.getIntValue("cno"),jsonObject.getIntValue("number"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		return RespMsgUtil.getSuccessResponseJoWithData(classRoom);
	}

	@RequestMapping("/updateCapacity")
	protected JSONObject updateCapacity(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("newCapacity")) || MjStringUtil.isEmpty(jsonObject.getIntValue("cno"))
				|| MjStringUtil.isEmpty(jsonObject.getIntValue("number"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}

		ClassRoom classRoom;
		try {
			classRoomRepository.updateCapacityByID(jsonObject.getIntValue("newCapacity"), jsonObject.getIntValue("cno"), jsonObject.getIntValue("number"));
			classRoom = classRoomRepository.findByCnoAndNumber(jsonObject.getIntValue("cno"),jsonObject.getIntValue("number"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		return RespMsgUtil.getSuccessResponseJoWithData(classRoom);
	}

	@RequestMapping("/updateNumber")
	protected JSONObject updateNumber(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("newNumber")) || MjStringUtil.isEmpty(jsonObject.getIntValue("cno"))
				|| MjStringUtil.isEmpty(jsonObject.getIntValue("number"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}

		ClassRoom classRoom;
		try {
			classRoomRepository.updateNumberByID(jsonObject.getIntValue("newNumber"), jsonObject.getIntValue("cno"), jsonObject.getIntValue("number"));
			classRoom = classRoomRepository.findByCnoAndNumber(jsonObject.getIntValue("cno"),jsonObject.getIntValue("newNumber"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		return RespMsgUtil.getSuccessResponseJoWithData(classRoom);
	}

	@RequestMapping("/updateClassRoom")
	protected JSONObject updateClassRoom(@RequestBody JSONObject jsonObject) {
		if(MjStringUtil.isEmpty(jsonObject)){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		int cno = jsonObject.getIntValue("cno");
		String place = jsonObject.getString("place");
		String time = jsonObject.getString("time");
		int capacity = jsonObject.getIntValue("capacity");
		int number = jsonObject.getIntValue("number");
		int newNumber = jsonObject.getIntValue("newNumber");

		if(classRoomRepository.findByCnoAndNumber(cno,newNumber)!=null){
			return RespMsgUtil.getFailResponseJoErrInsertExist();
		}
		ClassRoom classRoom;
		try {
			classRoom=classRoomRepository.findByCnoAndNumber(cno,number);
			classRoom.setCapacity(capacity);
			classRoom.setNumber(newNumber);
			classRoom.setPlace(place);
			classRoom.setTime(time);
			classRoom=classRoomRepository.save(classRoom);
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		return RespMsgUtil.getSuccessResponseJoWithData(classRoom);
	}


	
	@RequestMapping("/deleteByCno")
	protected JSONObject deleteByCno(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("cno"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}else if(classRoomRepository.findByCno(jsonObject.getIntValue("cno")) == null){
			return RespMsgUtil.getFailResponseJoErrDeleteNotExist();
		}
		Course course;
		int ID = jsonObject.getIntValue("cno");
		try {
			course = courseRepository.findByCourseID(ID);
			course.getClassrooms().clear();
			courseRepository.save(course);
			classRoomRepository.deleteByCno(ID);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
		return RespMsgUtil.getSuccessResponseJo();
	}
	
	@RequestMapping("/deleteByCnoAndNumber")
	protected JSONObject deleteByCnoNumber(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("cno")) || MjStringUtil.isEmpty(jsonObject.getIntValue("number"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}else if(classRoomRepository.findByCnoAndNumber(jsonObject.getIntValue("cno"),jsonObject.getIntValue("number")) == null){
			return RespMsgUtil.getFailResponseJoErrDeleteNotExist();
		}
		int cno = jsonObject.getIntValue("cno");
		int number = jsonObject.getIntValue("number");
		try {
				Course course = courseRepository.findByCourseID(cno);
				ClassRoom classRoom = classRoomRepository.findByCnoAndNumber(cno,number);
				course.getClassrooms().remove(classRoom);
				courseRepository.save(course);
				classRoomRepository.deleteByCnoAndNumber(jsonObject.getIntValue("cno"),jsonObject.getIntValue("number"));
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
		return RespMsgUtil.getSuccessResponseJo();
	}
	

	
}
