package com.check.controller;


import com.alibaba.fastjson.JSONObject;
import com.check.dao.CheckRepository;
import com.check.dao.RecordRepository;
import com.check.dao.StudentRepository;
import com.check.dao.ClassRoomRepository;
import com.check.entity.Check;
import com.check.entity.Record;
import com.check.util.ConvertDateUtil;
import com.check.util.MjStringUtil;
import com.check.util.RespMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;



@RestController
@Transactional
@CrossOrigin(origins = "*")
@RequestMapping("/checkIn")
public class CheckController {
	
	@Autowired
	private CheckRepository checkRepository;

	@Autowired
	private StudentRepository studentRepository;	

	@Autowired
	private ClassRoomRepository classRoomRepository;

	@Autowired
	private RecordRepository recordRepository;

	

	//返回某个课程的指定课堂的所有学生的记录
	@RequestMapping("/getByCnoAndClassRoom")
	protected JSONObject getByCnoAndClassRoom(@RequestBody JSONObject jsonObject) throws IOException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("cno"))
		|| MjStringUtil.isEmpty(jsonObject.getIntValue("classroom"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		List<Check> checks;
		try {
			checks = checkRepository.findByCnoAndClassroom(jsonObject.getIntValue("cno"),jsonObject.getIntValue("classroom"));
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

	//返回某个学生在某课程下的记录
	@RequestMapping("/getByCnoAndSno")
	protected JSONObject getByCnoAndSno(@RequestBody JSONObject jsonObject) throws IOException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("sno")) || MjStringUtil.isEmpty(jsonObject.getIntValue("cno"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		Check check;
		try {
			check = checkRepository.findByCnoAndSno(jsonObject.getIntValue("cno") , jsonObject.getIntValue("sno"));
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

	//返回某个学生在某课程下的记录
	@RequestMapping("/getByCnoAndSnoAndNumber")
	protected JSONObject getByCnoAndSnoAndNumber(@RequestBody JSONObject jsonObject) throws IOException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("sno")) || MjStringUtil.isEmpty(jsonObject.getIntValue("cno"))
				|| MjStringUtil.isEmpty(jsonObject.getIntValue("number"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		Record record;
		try {
			record = recordRepository.findByCnoAndSnoAndNumber(jsonObject.getIntValue("cno") , jsonObject.getIntValue("sno"),jsonObject.getIntValue("number"));
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		if(record == null){
			return RespMsgUtil.getFailResponseJoErrGetNotExist();
		}else{
			return  RespMsgUtil.getSuccessResponseJoWithData(record);
		}
	}

	//手动补签
	@RequestMapping("/change")
	protected JSONObject updateAttendenceBySnoAndCno(@RequestBody JSONObject jsonObject){
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("cno")) || MjStringUtil.isEmpty(jsonObject.getIntValue("sno"))
				|| MjStringUtil.isEmpty(jsonObject.getIntValue("number"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}
		int sno = jsonObject.getIntValue("sno");
		int cno = jsonObject.getIntValue("cno");
		int number = jsonObject.getIntValue("number");
		Record record = recordRepository.findByCnoAndSnoAndNumber(cno,sno,number);
		if(record == null){
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
		try {
			record.setAttendence(1);
			record=recordRepository.save(record);
			checkRepository.updateAttend_times(sno,cno);
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}
	
		return RespMsgUtil.getSuccessResponseJoWithData(record);
	}

	//开始签到
	@RequestMapping("/update")
	protected JSONObject update(@RequestBody JSONObject jsonObject) throws IOException, ParseException {
		if(jsonObject.isEmpty() || MjStringUtil.isEmpty(jsonObject.getIntValue("cno"))|| MjStringUtil.isEmpty(jsonObject.getIntValue("classroom"))){
			return RespMsgUtil.getFailResponseJoErrJSONError();
		}

		/**
		 * 取出课程号和课堂号
		 */
		int cno = jsonObject.getIntValue("cno");
		int classroom = jsonObject.getIntValue("classroom");
		List<Record> records;

		/**
		 * 将macID转换为对应的学号
		 */
		Set<String> macid = jsonObject.keySet();
		List<Integer> sno = new ArrayList<>();//用于存储MacID对应得学号的List
		for(String str:macid)//查询macid对应得学号并存储
		{
			if (str.equals("cno")==false && str.equals("classroom")==false){
			String id = jsonObject.getString(str);
			if(studentRepository.findByMacID(id)!=null)
			sno.add(studentRepository.findByMacID(id).getSno());
			}
		}


		/**
		 * 通过学号插入记录
		 */
		try {
			checkRepository.updateTotal(cno,classroom);
			classRoomRepository.updateTotalByID(cno,classroom);
			int total = classRoomRepository.findByCnoAndNumber(cno,classroom).getTotal();
			String time=ConvertDateUtil.Convert(new Date());
			List<Integer> snos = checkRepository.findSnoByCnoAndClassRoom(cno,classroom);
			Record record;
			records=new ArrayList<>();
			for(Integer stu:snos){ //遍历学号的List,并更新签到记录,存入map
                if (checkRepository.findByCnoAndSno(cno,stu)!=null) {
                if(sno.contains(stu)) {
					checkRepository.updateAttend_times(stu,cno);
					record = new Record(cno, stu, 1, time, total);
					Check check = checkRepository.findByCnoAndSno(cno,stu);
					recordRepository.save(record);
					check.getRecords().add(record);
					checkRepository.save(check);
					records.add(record);
				}else
				{
					record = new Record(cno, stu, 0, time, total);
					Check check = checkRepository.findByCnoAndSno(cno,stu);
					recordRepository.save(record);
					check.getRecords().add(record);
					checkRepository.save(check);
					records.add(record);
				}
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
			return RespMsgUtil.getFailResponseJoErrHibernate();
		}

		/**
		 * 获取课程下的所有签到记录，并返回
		 */
		return  RespMsgUtil.getSuccessResponseJoWithData(records);
	}
	

	
	
	
}
