package com.check.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.check.dao.CheckRepository;
import com.check.dao.StudentRepository;
import com.check.entity.Check;



@RestController
@RequestMapping("/checkIn")
public class CheckController {
	
	@Autowired
	private CheckRepository checkRepository;

	@Autowired
	private StudentRepository studentRepository;	
	
	
	@RequestMapping("/add")
	public String add(@RequestBody Check check) {
		System.out.println(check);
		if(checkRepository.findBySnoAndCno(check.getSno(), check.getCno())!=null) {
			return "该记录已存在";
		}
		checkRepository.save(check);
		return "保存成功";
	}
	
	@RequestMapping("/findBySno")
	public List<Check> findBySno(int sno){
		return checkRepository.findBySno(sno);
	}
	
	@RequestMapping("/findBySname")
	public List<Check> findBySname(String sname){
		return checkRepository.findBySname(sname);
	}
	
	@RequestMapping("/findByCno")
	public List<Check> findByCno(int cno){
		return checkRepository.findByCno(cno);
	}

	@RequestMapping("/findBySnoAndCno")
	public Check findBySnoAndCno(int sno,int cno){
		return checkRepository.findBySnoAndCno(sno,cno);
	}
	
	@RequestMapping("/findBySnoAndCname")
	public Check findBySnoAndCname(int sno,String cname){
		return checkRepository.findBySnoAndCname(sno,cname);
	}
	
	@RequestMapping("/updateAttendenceBySnoAndCno")
	public String updateAttendenceBySnoAndCno(int attendence,int sno,int cno){
		checkRepository.updateAttendenceBySnoAndCno( attendence, sno, cno);
		return "更新成功";
	}
	
	@RequestMapping("/updateAttend_timesBySnoAndCno")
	public String updateAttend_timesBySnoAndCno(int sno,int cno){
		checkRepository.updateAttend_timesBySnoAndCno(sno, cno);
		return "更新成功";
	}
	
	//本方法接收String类型的mac地址的数组以及对应得课程号,并自动更新签到记录,以Map的形式返回出勤的学生以及学号
	@RequestMapping("/update")
	public Map<Integer,String> update( String macid[], int cno){
		checkRepository.resetAttendence(cno);//先重置记录
		Map<Integer, String> students = new HashMap<Integer,String>();//定义用于返回的map
		int s;
		List<Integer> sno = new ArrayList<>();//用于存储MacID对应得学号的List
		for(int i = 0;i<macid.length;i++)//查询macid对应得学号并存储
			{
			String str =macid[i];
			if(studentRepository.findByMacID(str)!=null)
			sno.add(studentRepository.findByMacID(str).getSno());
			}
		Iterator<Integer> stu =sno.iterator();
		while(stu.hasNext()) {//遍历学号的List,并更新签到记录,存入map
			s=stu.next();
			//System.out.println(s);
			if (checkRepository.findBySnoAndCno(s,cno)!=null) {
			checkRepository.updateAttendence(s,cno);
			students.put(s,studentRepository.findBySno(s).getName());
			}
		}
		checkRepository.updateAttend_times(cno);
		return students;
	}
	
	
}
