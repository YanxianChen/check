package com.check.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.check.dao.CourseRepository;
import com.check.dao.TeacherRepository;
import com.check.dao.Teacher_ClassRepository;
import com.check.entity.Teacher_Class;
import com.check.util.MjJSONUtil;
import com.check.util.MjStringUtil;
import com.check.util.RespMsgUtil;

@RestController
@RequestMapping("/Teacher_Class")
public class Teacher_ClassController {
	
	@Autowired
	private Teacher_ClassRepository teacher_classRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	//添加
	@RequestMapping(value="/add")
	protected JSONObject add(@RequestBody JSONObject jsonObject) throws IOException {
		JSONObject jo = null;
		int tno = jsonObject.getIntValue("tno");
		String tname = jsonObject.getString("tname");
		int cno = jsonObject.getIntValue("cno");
		String cname = jsonObject.getString("cname");
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(cno) || MjStringUtil.isEmpty(cname) || MjStringUtil.isEmpty(tno) ||
				MjStringUtil.isEmpty(tname)){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		if(teacherRepository.findByTno(tno)==null){
			return RespMsgUtil.getFailResponseJoWithErrMsg("该老师未注册");
		}else if(courseRepository.findByCourseID(cno)==null){
			return RespMsgUtil.getFailResponseJoWithErrMsg("该课程不在列表中");
		}
		Teacher_Class teacher_class = MjJSONUtil.jsonToBean(jsonObject,Teacher_Class.class);
		if(teacher_classRepository.findByTnoAndCno(teacher_class.getTno(),teacher_class.getCno())!=null){
			return RespMsgUtil.getFailResponseJoErrInsertExist();
		}
		
		try {
		teacher_class = teacher_classRepository.save(teacher_class);
		jo = RespMsgUtil.getSuccessResponseJoWithData(teacher_class);
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		
		
		return jo;
	}
	
	@RequestMapping("/getByTno")
	protected JSONObject getByTno(@RequestBody JSONObject jsonObject) throws IOException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("tno"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		List<Teacher_Class> teacher_class;
		try {
			teacher_class = teacher_classRepository.findByTno(jsonObject.getIntValue("tno"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		if(teacher_class == null){
			return RespMsgUtil.getFailResponseJoErrGetNotExist();
		}else{
			return  RespMsgUtil.getSuccessResponseJoWithData(teacher_class);
		}
	}
	
	@RequestMapping("/getByCno")
	protected JSONObject getByCno(@RequestBody JSONObject jsonObject) throws IOException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("cno"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		List<Teacher_Class> teacher_class;
		try {
			teacher_class = teacher_classRepository.findByCno(jsonObject.getIntValue("cno"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		if(teacher_class == null){
			return RespMsgUtil.getFailResponseJoErrGetNotExist();
		}else{
			return  RespMsgUtil.getSuccessResponseJoWithData(teacher_class);
		}
	}
	
	@RequestMapping("/getByTnoAndCno")
	protected JSONObject getByTnoAndCno(@RequestBody JSONObject jsonObject) throws IOException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("tno")) || MjStringUtil.isEmpty(jsonObject.getIntValue("cno"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		Teacher_Class teacher_class;
		try {
			teacher_class = teacher_classRepository.findByTnoAndCno(jsonObject.getIntValue("tno"),jsonObject.getIntValue("cno"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		if(teacher_class == null){
			return RespMsgUtil.getFailResponseJoErrGetNotExist();
		}else{
			return  RespMsgUtil.getSuccessResponseJoWithData(teacher_class);
		}
	}
	
	@RequestMapping("/deleteByTno")
	protected JSONObject deleteByTno(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("tno"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}else if(teacher_classRepository.findByTno(jsonObject.getIntValue("tno")) == null){
			return RespMsgUtil.getFailResponseJoErrDeleteNotExist();
		}
		
		try {
				teacher_classRepository.deleteByTno(jsonObject.getIntValue("tno"));
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
		return RespMsgUtil.getSuccessResponseJo();
	}
	
	@RequestMapping("/deleteByCno")
	protected JSONObject deleteByCno(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("cno"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}else if(teacher_classRepository.findByTno(jsonObject.getIntValue("cno")) == null){
			return RespMsgUtil.getFailResponseJoErrDeleteNotExist();
		}
		
		try {
				teacher_classRepository.deleteByTno(jsonObject.getIntValue("cno"));
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
		return RespMsgUtil.getSuccessResponseJo();
	}
	
	@RequestMapping("/deleteByTnoAndCno")
	protected JSONObject deleteByTnoAndCno(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("tno")) || MjStringUtil.isEmpty(jsonObject.getIntValue("cno"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}else if(teacher_classRepository.findByTnoAndCno(jsonObject.getIntValue("tno"),jsonObject.getIntValue("cno")) == null){
			return RespMsgUtil.getFailResponseJoErrDeleteNotExist();
		}
		
		try {
				teacher_classRepository.deleteByTnoAndCno(jsonObject.getIntValue("tno"),jsonObject.getIntValue("cno"));
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
		return RespMsgUtil.getSuccessResponseJo();
	}
	
	@RequestMapping("/deleteByTnoAndCname")
	protected JSONObject deleteByTnoAndCname(@RequestBody JSONObject jsonObject) {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("tno")) || MjStringUtil.isEmpty(jsonObject.getString("cname"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}else if(teacher_classRepository.findByTnoAndCname(jsonObject.getIntValue("tno"),jsonObject.getString("cname")) == null){
			return RespMsgUtil.getFailResponseJoErrDeleteNotExist();
		}
		
		try {
				teacher_classRepository.deleteByTnoAndCname(jsonObject.getIntValue("tno"),jsonObject.getString("cname"));
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return RespMsgUtil.getFailResponseJoErrHibernate();
			}
		return RespMsgUtil.getSuccessResponseJo();
	}
	
}
