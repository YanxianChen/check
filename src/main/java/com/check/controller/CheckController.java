package com.check.controller;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.alibaba.fastjson.JSONObject;
import com.check.dao.CheckRepository;
import com.check.dao.CourseRepository;
import com.check.dao.StudentRepository;
import com.check.entity.Check;
import com.check.util.ConvertDateUtil;
import com.check.util.MjJSONUtil;
import com.check.util.MjStringUtil;
import com.check.util.RespMsgUtil;



@RestController
@RequestMapping("/checkIn")
public class CheckController {
	
	@Autowired
	private CheckRepository checkRepository;

	@Autowired
	private StudentRepository studentRepository;	
	
	@Autowired
	private CourseRepository courseRepository;
	
	@RequestMapping("/add")
	public JSONObject add(@RequestBody JSONObject jsonObject) throws IOException {
		JSONObject jo = null;
		int sno = jsonObject.getIntValue("sno");
		String sname = jsonObject.getString("sname");
		int cno = jsonObject.getIntValue("cno");
		String cname = jsonObject.getString("cname");
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(sno) || MjStringUtil.isEmpty(sname) || MjStringUtil.isEmpty(cno) ||
				MjStringUtil.isEmpty(cname)){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		if(studentRepository.findBySno(sno)==null){
			return RespMsgUtil.getFailResponseJoWithErrMsg("该学生不在列表中");
		}else if(courseRepository.findByCourseID(cno)==null){
			return RespMsgUtil.getFailResponseJoWithErrMsg("该课程不在列表中");
		}
		jsonObject.put("attendence",0);
		jsonObject.put("attend_times",0);
		try {
			jsonObject.put("time", ConvertDateUtil.Convert(new Date()));
		} catch (ParseException e1) {
			e1.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		
		Check check = MjJSONUtil.jsonToBean(jsonObject, Check.class);
		if(checkRepository.findBySnoAndCno(check.getSno(),check.getCno())!=null){
			return RespMsgUtil.getFailResponseJoErrInsertExist();
		}
		
		try {
		check = checkRepository.save(check);
		jo = RespMsgUtil.getSuccessResponseJoWithData(check);
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		
		
		return jo;
	}
	
	@RequestMapping("/getBySno")
	protected JSONObject getBySno(@RequestBody JSONObject jsonObject) throws IOException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("sno"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		List<Check> checks;
		try {
			checks = checkRepository.findBySno(jsonObject.getIntValue("sno"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		if(checks == null){
			return RespMsgUtil.getFailResponseJoErrGetNotExist();
		}else{
			return  RespMsgUtil.getSuccessResponseJoWithData(checks);
		}
	}
	
	@RequestMapping("/getBySname")
	protected JSONObject getBySname(@RequestBody JSONObject jsonObject) throws IOException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getString("sname"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		List<Check> checks;
		try {
			checks = checkRepository.findBySname(jsonObject.getString("sname"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		if(checks == null){
			return RespMsgUtil.getFailResponseJoErrGetNotExist();
		}else{
			return  RespMsgUtil.getSuccessResponseJoWithData(checks);
		}
	}
	
	@RequestMapping("/getByCno")
	protected JSONObject getByCno(@RequestBody JSONObject jsonObject) throws IOException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("cno"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		List<Check> checks;
		try {
			checks = checkRepository.findByCno(jsonObject.getIntValue("cno"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		if(checks == null){
			return RespMsgUtil.getFailResponseJoErrGetNotExist();
		}else{
			return  RespMsgUtil.getSuccessResponseJoWithData(checks);
		}
	}

	@RequestMapping("/getBySnoAndCno")
	protected JSONObject getBySnoAndCno(@RequestBody JSONObject jsonObject) throws IOException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("sno")) || MjStringUtil.isEmpty(jsonObject.getIntValue("cno"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		Check check;
		try {
			check = checkRepository.findBySnoAndCno(jsonObject.getIntValue("sno") , jsonObject.getIntValue("cno"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		if(check == null){
			return RespMsgUtil.getFailResponseJoErrGetNotExist();
		}else{
			return  RespMsgUtil.getSuccessResponseJoWithData(check);
		}
	}
	
	@RequestMapping("/getBySnoAndCname")
	protected JSONObject getBySnoAndCname(@RequestBody JSONObject jsonObject) throws IOException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("sno")) || MjStringUtil.isEmpty(jsonObject.getString("cname"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		Check check;
		try {
			check = checkRepository.findBySnoAndCname(jsonObject.getIntValue("sno") , jsonObject.getString("cname"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		if(check == null){
			return RespMsgUtil.getFailResponseJoErrGetNotExist();
		}else{
			return RespMsgUtil.getSuccessResponseJoWithData(check);
		}
	}
	
	//手动补签
	@RequestMapping("/change")
	public JSONObject updateAttendenceBySnoAndCno(@RequestBody JSONObject jsonObject){
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("sno")) || MjStringUtil.isEmpty(jsonObject.getIntValue("cno"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		int sno = jsonObject.getIntValue("sno");
		int cno = jsonObject.getIntValue("cno");
		Check check=checkRepository.findBySnoAndCno(sno, cno);
		if(check == null){
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		try {
			checkRepository.updateAttendenceBySnoAndCno(sno,cno);
			checkRepository.updateAttend_timesBySnoAndCno( sno,cno);
			check = checkRepository.findBySnoAndCno(sno, cno);
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
	
		return RespMsgUtil.getSuccessResponseJoWithData(check);
	}
	
	
	//开始签到
	@RequestMapping("/update")
	public JSONObject update(@RequestBody JSONObject jsonObject) throws ParseException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("cno"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		System.out.println("1");
		int cno = jsonObject.getIntValue("cno");
		checkRepository.resetAttendence(jsonObject.getIntValue("cno"));//先重置记录
		Set<String> macid = jsonObject.keySet();
		List<Integer> sno = new ArrayList<>();//用于存储MacID对应得学号的List
		for(String str:macid)//查询macid对应得学号并存储
		{
			if (str.equals("cno")==false){
			String id = jsonObject.getString(str);
			if(studentRepository.findByMacID(id)!=null)
			sno.add(studentRepository.findByMacID(id).getSno());
			}
		}

		List<Check> checks = new ArrayList<>();
		for(Integer stu:sno){ //遍历学号的List,并更新签到记录,存入map
			if (checkRepository.findBySnoAndCno(stu,cno)!=null) {
			checkRepository.updateAttendenceBySnoAndCno(stu,cno);
			checkRepository.updateTimeBySnoAndCno(ConvertDateUtil.Convert(new Date()),stu, cno);
			checks.add(checkRepository.findBySnoAndCno(stu, cno));
			}
		}
		checkRepository.updateAttend_times(cno);
		return  RespMsgUtil.getSuccessResponseJoWithData(checks);
	}
	
	
}
