package com.check.controller;



import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.check.dao.TeacherRepository;
import com.check.entity.Teacher;
import com.check.util.MjJSONUtil;
import com.check.util.MjStringUtil;
import com.check.util.RespMsgUtil;




@RestController
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
		//添加
		@RequestMapping(value="/add")
		protected JSONObject add(@RequestBody JSONObject jsonObject) throws IOException {
			JSONObject jo = null;
			int tno = jsonObject.getIntValue("tno");
			String name = jsonObject.getString("name");
			String password = jsonObject.getString("password");
			String gender = jsonObject.getString("gender");
			if(jsonObject.isEmpty() || MjStringUtil.isEmpty(tno) || MjStringUtil.isEmpty(name) || MjStringUtil.isEmpty(password) ||
					MjStringUtil.isEmpty(gender)){
				return RespMsgUtil.getFailResponseJoErrJSONError();
			}
			
			Teacher teacher = MjJSONUtil.jsonToBean(jsonObject,Teacher.class);
			if(teacherRepository.findByTno(teacher.getTno())!=null){
				return RespMsgUtil.getFailResponseJoErrInsertExist();
			}
			
			try {
			teacher = teacherRepository.save(teacher);
			jo = RespMsgUtil.getSuccessResponseJoWithData(teacher);
			} catch (Exception e) {
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
			
			
			return jo;
		}
		
		
		//查询
		@RequestMapping("/getByTno")
		protected JSONObject getByTno(@RequestBody JSONObject jsonObject) throws IOException {
			if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("tno"))){
				return RespMsgUtil.getFailResponseJoErrJSONError();
			}
			Teacher teacher;
			try {
				teacher = teacherRepository.findByTno(jsonObject.getIntValue("tno"));
			} catch (Exception e) {
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
			if(teacher == null){
				return RespMsgUtil.getFailResponseJoErrGetNotExist();
			}else{
				return  RespMsgUtil.getSuccessResponseJoWithData(teacher);
			}
		}
		
		@RequestMapping("/getByName")
		protected JSONObject getByName(@RequestBody JSONObject jsonObject) throws IOException {
			if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("name"))){
				return RespMsgUtil.getFailResponseJoErrJSONError();
			}
			List<Teacher> teachers;
			try {
				teachers = teacherRepository.findByName(jsonObject.getString("name"));
			} catch (Exception e) {
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
			if(teachers == null){
				return RespMsgUtil.getFailResponseJoErrGetNotExist();
			}else{
				return RespMsgUtil.getSuccessResponseJoWithData(teachers);
			}
		}
		
	
		
		@RequestMapping("/getAll")
		protected JSONObject  getAll() throws IOException{
			
			List<Teacher> teachers;
			try {
				teachers = teacherRepository.findAll();
			} catch (Exception e) {
				e.printStackTrace();
			return	add(RespMsgUtil.getFailResponseJoErrHibernate());
			
			}
			
			return RespMsgUtil.getSuccessResponseJoWithData(teachers);
		}
		
		@RequestMapping("/getByGender")
		protected JSONObject getByGender(@RequestBody JSONObject jsonObject) throws IOException{
			if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("sex"))){
				return (RespMsgUtil.getFailResponseJoErrJSONError());
				 
			}
			List<Teacher> teachers;
			try {
				teachers = teacherRepository.findByGender(jsonObject.getString("sex"));
				System.out.println(teachers);
			} catch (Exception e) {
				e.printStackTrace();
				return (RespMsgUtil.getFailResponseJoErrHibernate());
				 
			}
			
			return RespMsgUtil.getSuccessResponseJoWithData(teachers);
		}
		
		@RequestMapping("/getPassword")
		protected JSONObject getPasswordByTno(@RequestBody JSONObject jsonObject) throws IOException{
			if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("tno"))){
				return (RespMsgUtil.getFailResponseJoErrJSONError());
				 
			}
			String password;
			try {
				password = teacherRepository.findByTno(jsonObject.getIntValue("tno")).getPassword();
			} catch (Exception e) {
				e.printStackTrace();
				return (RespMsgUtil.getFailResponseJoErrHibernate());
				 
			}
			JSONObject jo = new JSONObject();
			jo.put("password", password);
			return RespMsgUtil.getSuccessResponseJoWithData(jo);
		}
		
		
		//返回记录的总数
		@RequestMapping("/count")
		protected JSONObject count() {
			JSONObject jsonObject = new JSONObject();
			try {
				long count = teacherRepository.count();
				jsonObject.put("count",count);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
			return jsonObject;
		}
		
		//删除
		@RequestMapping("/deleteByTno")
		protected JSONObject deleteByTno(@RequestBody JSONObject jsonObject) {
			if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("tno"))){
				return RespMsgUtil.getFailResponseJoErrJSONError();
			}else if(teacherRepository.findByTno(jsonObject.getIntValue("tno")) == null){
				return RespMsgUtil.getFailResponseJoErrDeleteNotExist();
			}
			
			try {
					teacherRepository.deleteByTno(jsonObject.getIntValue("tno"));
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
				teacherRepository.deleteAll();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
			return RespMsgUtil.getSuccessResponseJo();
		}
		
		
		//更新
		@RequestMapping("/updateName")
		protected JSONObject updateNameByTno(@RequestBody JSONObject jsonObject) {
			if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("newName")) || MjStringUtil.isEmpty(jsonObject.getIntValue("tno"))){
				return RespMsgUtil.getFailResponseJoErrJSONError();
			}
			
			Teacher teacher;
			teacher = teacherRepository.findByTno(jsonObject.getIntValue("tno"));
			if(teacher==null){
				return RespMsgUtil.getFailResponseJoErrUpdateNotExist();
			}
			try {
				teacherRepository.updateNameByTno(jsonObject.getString("newName"), jsonObject.getIntValue("tno"));
				teacher = teacherRepository.findByTno(jsonObject.getIntValue("tno"));
			} catch (Exception e) {
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
			return RespMsgUtil.getSuccessResponseJoWithData(teacher);
		}
		
		@RequestMapping("/updateSex")
		protected JSONObject updateSexByTno(@RequestBody JSONObject jsonObject) {
			if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("newSex")) || MjStringUtil.isEmpty(jsonObject.getIntValue("tno"))){
				return RespMsgUtil.getFailResponseJoErrJSONError();
			}
			
			Teacher teacher;
			teacher = teacherRepository.findByTno(jsonObject.getIntValue("tno"));
			if(teacher==null){
				return RespMsgUtil.getFailResponseJoErrUpdateNotExist();
			}
			try {
				teacherRepository.updateSexByTno(jsonObject.getString("newSex"), jsonObject.getIntValue("tno"));
				teacher = teacherRepository.findByTno(jsonObject.getIntValue("tno"));
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
			return RespMsgUtil.getSuccessResponseJoWithData(teacher); 
		}
		

		@RequestMapping("/updatePassword")
		protected JSONObject updatePasswordBySno(@RequestBody JSONObject jsonObject) {
			if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("newPassword")) || MjStringUtil.isEmpty(jsonObject.getIntValue("tno"))){
				return RespMsgUtil.getFailResponseJoErrJSONError();
			}
			
			Teacher teacher;
			teacher = teacherRepository.findByTno(jsonObject.getIntValue("tno"));
			if(teacher==null){
				return RespMsgUtil.getFailResponseJoErrUpdateNotExist();
			}
			try {
				teacherRepository.updatePasswordByTno(jsonObject.getString("newPassword"), jsonObject.getIntValue("tno"));
				teacher = teacherRepository.findByTno(jsonObject.getIntValue("tno"));
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
			return RespMsgUtil.getSuccessResponseJoWithData(teacher); 
		}
	
	
	
}
