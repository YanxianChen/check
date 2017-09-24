package com.check.controller;


import com.alibaba.fastjson.JSONObject;
import com.check.dao.TeacherRepository;
import com.check.entity.Teacher;
import com.check.util.MjJSONUtil;
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
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
		//邮箱加密码注册
		@RequestMapping(value="/add")
		protected JSONObject add(@RequestBody JSONObject jsonObject) throws IOException {
			JSONObject jo ;
			String password = jsonObject.getString("password");
			String email = jsonObject.getString("email");
			if(jsonObject.isEmpty() ||  MjStringUtil.isEmpty(password) ||
					MjStringUtil.isEmpty(email)){
				return RespMsgUtil.getFailResponseJoErrJSONError();
			}
			
			Teacher teacher = MjJSONUtil.jsonToBean(jsonObject,Teacher.class);
			if(teacherRepository.findByEmail(teacher.getEmail())!=null){
				return RespMsgUtil.getFailResponseJoWithErrMsg("该邮箱已经被注册");
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


		//工号查询
		@RequestMapping("/getByTno")
		protected JSONObject getByTno(@RequestBody JSONObject jsonObject) throws IOException {
			if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("tno"))){
				return RespMsgUtil.getFailResponseJoErrJSONError();
			}
			if(teacherRepository.findByTno(jsonObject.getIntValue("tno"))==null){
				return (RespMsgUtil.getFailResponseJoErrGetNotExist());
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

		//邮箱查询
		@RequestMapping("/getByEmail")
		protected JSONObject getByEmail(@RequestBody JSONObject jsonObject) throws IOException {
			if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("email"))){
				return RespMsgUtil.getFailResponseJoErrJSONError();
			}
			if(teacherRepository.findByEmail(jsonObject.getString("email"))==null){
				return (RespMsgUtil.getFailResponseJoErrGetNotExist());
			}
			Teacher teacher;
			try {
				teacher = teacherRepository.findByEmail(jsonObject.getString("email"));
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

		//姓名查询
		@RequestMapping("/getByName")
		protected JSONObject getByName(@RequestBody JSONObject jsonObject) throws IOException {
			if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("name"))){
				return RespMsgUtil.getFailResponseJoErrJSONError();
			}
			if(teacherRepository.findByName(jsonObject.getString("name"))==null){
				return (RespMsgUtil.getFailResponseJoErrGetNotExist());
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

		//通过工号查询密码
		@RequestMapping("/getPasswordByTno")
		protected JSONObject getPasswordByTno(@RequestBody JSONObject jsonObject) throws IOException{
			if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("tno"))){
				return (RespMsgUtil.getFailResponseJoErrJSONError());
				 
			}
			if(teacherRepository.findByTno(jsonObject.getIntValue("tno"))==null){
				return (RespMsgUtil.getFailResponseJoErrGetNotExist());
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

		//通过邮箱查询密码
		@RequestMapping("/getPasswordByEmail")
		protected JSONObject getPasswordByEmail(@RequestBody JSONObject jsonObject) throws IOException{
			if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("email"))){
				return (RespMsgUtil.getFailResponseJoErrJSONError());

			}
			if(teacherRepository.findByEmail(jsonObject.getString("email"))==null){
				return (RespMsgUtil.getFailResponseJoErrGetNotExist());
			}
			String password;
			try {
				password = teacherRepository.findByEmail(jsonObject.getString("email")).getPassword();
			} catch (Exception e) {
				e.printStackTrace();
				return (RespMsgUtil.getFailResponseJoErrHibernate());

			}
			JSONObject jo = new JSONObject();
			jo.put("password", password);
			return RespMsgUtil.getSuccessResponseJoWithData(jo);
		}


		//更新
		@RequestMapping("/updateNameByTno")
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
		
		@RequestMapping("/updateSexByTno")
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

		@RequestMapping("/updatePasswordByTno")
		protected JSONObject updatePasswordByTno(@RequestBody JSONObject jsonObject) {
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


		@RequestMapping("/updateTnoByEmail")
		protected JSONObject updateTnoByEmail(@RequestBody JSONObject jsonObject) {
			if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("tno")) || MjStringUtil.isEmpty(jsonObject.getString("email"))){
				return RespMsgUtil.getFailResponseJoErrJSONError();
			}

			Teacher teacher;
			teacher = teacherRepository.findByEmail(jsonObject.getString("email"));
			if(teacher==null){
				return RespMsgUtil.getFailResponseJoErrUpdateNotExist();
			}else if(teacherRepository.findByTno(jsonObject.getIntValue("tno"))!=null){
				return RespMsgUtil.getFailResponseJoWithErrMsg("该工号已被注册");
			}

			try {
				teacherRepository.updateTnoByEmail(jsonObject.getIntValue("tno"), jsonObject.getString("email"));
				teacher = teacherRepository.findByEmail(jsonObject.getString("email"));
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
			return RespMsgUtil.getSuccessResponseJoWithData(teacher);
		}

		@RequestMapping("/updateNameByEmail")
		protected JSONObject updateNameByEmail(@RequestBody JSONObject jsonObject) {
			if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("name")) || MjStringUtil.isEmpty(jsonObject.getString("email"))){
				return RespMsgUtil.getFailResponseJoErrJSONError();
			}

			Teacher teacher;
			teacher = teacherRepository.findByEmail(jsonObject.getString("email"));
			if(teacher==null){
				return RespMsgUtil.getFailResponseJoErrUpdateNotExist();
			}
			try {
				teacherRepository.updateNameByEmail(jsonObject.getString("name"), jsonObject.getString("email"));
				teacher = teacherRepository.findByEmail(jsonObject.getString("email"));
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
			return RespMsgUtil.getSuccessResponseJoWithData(teacher);
		}

		@RequestMapping("/updateSexByEmail")
		protected JSONObject updateSexByEmail(@RequestBody JSONObject jsonObject) {
			if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("sex")) || MjStringUtil.isEmpty(jsonObject.getString("email"))){
				return RespMsgUtil.getFailResponseJoErrJSONError();
			}

			Teacher teacher;
			teacher = teacherRepository.findByEmail(jsonObject.getString("email"));
			if(teacher==null){
				return RespMsgUtil.getFailResponseJoErrUpdateNotExist();
			}
			try {
				teacherRepository.updateGenderByEmail(jsonObject.getString("sex"), jsonObject.getString("email"));
				teacher = teacherRepository.findByEmail(jsonObject.getString("email"));
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
			return RespMsgUtil.getSuccessResponseJoWithData(teacher);
		}

		@RequestMapping("/updatePasswordByEmail")
		protected JSONObject updatePasswordByEmail(@RequestBody JSONObject jsonObject) {
			if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("password")) || MjStringUtil.isEmpty(jsonObject.getString("email"))){
				return RespMsgUtil.getFailResponseJoErrJSONError();
			}

			Teacher teacher;
			teacher = teacherRepository.findByEmail(jsonObject.getString("email"));
			if(teacher==null){
				return RespMsgUtil.getFailResponseJoErrUpdateNotExist();
			}
			try {
				teacherRepository.updatePasswordByEmail(jsonObject.getString("password"), jsonObject.getString("email"));
				teacher = teacherRepository.findByEmail(jsonObject.getString("email"));
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
			return RespMsgUtil.getSuccessResponseJoWithData(teacher);
		}





}
