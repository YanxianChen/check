package com.check.controller;



import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.check.dao.StudentRepository;
import com.check.entity.Student;
import com.check.util.MjJSONUtil;
import com.check.util.MjStringUtil;
import com.check.util.RespMsgUtil;




@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired//自动加载StudentRepository接口的实体bean
	private StudentRepository studentRepository;
	
	//添加学生
	@RequestMapping(value="/add")
	protected JSONObject add(@RequestBody JSONObject jsonObject) throws IOException {
		JSONObject jo = null;
		int sno = jsonObject.getIntValue("sno");
		String name = jsonObject.getString("name");
		String macID = jsonObject.getString("macID");
		String gender = jsonObject.getString("gender");
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(sno) || MjStringUtil.isEmpty(name) || MjStringUtil.isEmpty(macID) ||
				MjStringUtil.isEmpty(gender)){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		
		Student student = MjJSONUtil.jsonToBean(jsonObject, Student.class);
		if(studentRepository.findBySno(student.getSno())!=null || 
				studentRepository.findByMacID(student.getMacID())!=null){
			return RespMsgUtil.getFailResponseJoErrInsertExist();
		}
		
		try {
		student = studentRepository.save(student);
		jo = RespMsgUtil.getSuccessResponseJoWithData(student);
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		
		
		return jo;
	}
	
	
	//查询
	@RequestMapping("/getBySno")
	protected JSONObject getBySno(@RequestBody JSONObject jsonObject) throws IOException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("sno"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		Student student;
		try {
			student = studentRepository.findBySno(jsonObject.getIntValue("sno"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		if(student == null){
			return RespMsgUtil.getFailResponseJoErrGetNotExist();
		}else{
			return  RespMsgUtil.getSuccessResponseJoWithData(student);
		}
	}
	
	@RequestMapping("/getByName")
	protected JSONObject getByName(@RequestBody JSONObject jsonObject) throws IOException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("name"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		List<Student> students;
		try {
			students = studentRepository.findByName(jsonObject.getString("name"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		if(students == null){
			return RespMsgUtil.getFailResponseJoErrGetNotExist();
		}else{
			return RespMsgUtil.getSuccessResponseJoWithData(students);
		}
	}
	
	@RequestMapping("/getByMacID")
	protected JSONObject getByMacID(@RequestBody JSONObject jsonObject) throws IOException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("macID"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		Student student;
		try {
			student = studentRepository.findByMacID(jsonObject.getString("macID"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		if(student == null){
			return RespMsgUtil.getFailResponseJoErrGetNotExist();
		}else{
			return RespMsgUtil.getSuccessResponseJoWithData(student);
		}
	}
	
	@RequestMapping("/getAll")
	protected JSONObject  getAll() throws IOException{
		
		List<Student> students;
		try {
			students = studentRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		return	add(RespMsgUtil.getFailResponseJoErrHibernate());
		
		}
		
		return RespMsgUtil.getSuccessResponseJoWithData(students);
	}
	
	@RequestMapping("/getByGender")
	protected JSONObject getByGender(@RequestBody JSONObject jsonObject) throws IOException{
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("sex"))){
			return (RespMsgUtil.getFailResponseJoErrJSONError());
			 
		}
		List<Student> students;
		try {
			students = studentRepository.findByGender(jsonObject.getString("sex"));
		} catch (Exception e) {
			e.printStackTrace();
			return (RespMsgUtil.getFailResponseJoErrHibernate());
			 
		}
		
		return RespMsgUtil.getSuccessResponseJoWithData(students);
	}
	
	
	
	//返回记录的总数
	@RequestMapping("/count")
	protected JSONObject count() {
		JSONObject jsonObject = new JSONObject();
		try {
			long count = studentRepository.count();
			System.out.println(count);
			jsonObject.put("count",count);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		return RespMsgUtil.getSuccessResponseJoWithData(jsonObject);
	}
	
	//删除
	@RequestMapping("/deleteBySno")
	protected JSONObject deleteBySno(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("sno"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}else if(studentRepository.findBySno(jsonObject.getIntValue("sno")) == null){
			return RespMsgUtil.getFailResponseJoErrDeleteNotExist();
		}
		
		try {
				studentRepository.deleteBySno(jsonObject.getIntValue("sno"));
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
		return RespMsgUtil.getSuccessResponseJo();
	}
	
	@RequestMapping("/deleteByMacID")
	protected JSONObject deleteByMacID(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("macID"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}else if(studentRepository.findByMacID(jsonObject.getString("macID")) == null){
			return RespMsgUtil.getFailResponseJoErrDeleteNotExist();
		}
		
		try {
				studentRepository.deleteByMacID(jsonObject.getString("macID"));
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
		return RespMsgUtil.getSuccessResponseJo();
	}
	
	@RequestMapping("/deleteAll")
	protected JSONObject deleteAll() {
		try {
			studentRepository.deleteAll();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		return RespMsgUtil.getSuccessResponseJo();
	}
	
	
	//更新
	@RequestMapping("/updateName")
	protected JSONObject updateNameBySno(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("newName")) || MjStringUtil.isEmpty(jsonObject.getIntValue("sno"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		
		Student student;
		student = studentRepository.findBySno(jsonObject.getIntValue("sno"));
		if(student==null){
			return RespMsgUtil.getFailResponseJoErrUpdateNotExist();
		}
		try {
			studentRepository.updateNameBySno(jsonObject.getString("newName"), jsonObject.getIntValue("sno"));
			student = studentRepository.findBySno(jsonObject.getIntValue("sno"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		return RespMsgUtil.getSuccessResponseJoWithData(student);
	}
	
	@RequestMapping("/updateSex")
	protected JSONObject updateSexBySno(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("newSex")) || MjStringUtil.isEmpty(jsonObject.getIntValue("sno"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		
		Student student;
		student = studentRepository.findBySno(jsonObject.getIntValue("sno"));
		if(student==null){
			return RespMsgUtil.getFailResponseJoErrUpdateNotExist();
		}
		try {
			studentRepository.updateSexBySno(jsonObject.getString("newSex"), jsonObject.getIntValue("sno"));
			student = studentRepository.findBySno(jsonObject.getIntValue("sno"));
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		return RespMsgUtil.getSuccessResponseJoWithData(student); 
	}
	
	
}
