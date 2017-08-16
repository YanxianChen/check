package com.check.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.check.dao.StudentRepository;
import com.check.entity.Student;



/**
 * Created by yancychan on 17-6-28.
 */
@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired//自动加载StudentRepository接口的实体bean
	private StudentRepository studentRepository;
	
	//添加学生
	@RequestMapping(value="/add")
	public Student add(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	
	//查询
	@RequestMapping("/getBySno")
	public Student getBySno(int sno) {
		return studentRepository.findBySno(sno);
	}
	
	@RequestMapping("/getByName")
	public Student getByName(String name) {
		return studentRepository.findByName(name);
	}
	
	@RequestMapping("/getByMacID")
	public Student getByMacID(String macID) {
		return studentRepository.findByMacID(macID);
	}
	
	@RequestMapping("/getByNameAndSno")
	public Student getByNameAndSno(String name,int sno) {
		return studentRepository.findByNameAndSno(name,sno);
	}
	
	@RequestMapping("/getAll")
	public List<Student> getAll(){
		return studentRepository.findAll();
	}
	
	@RequestMapping("/getByGender")
	public List<Student> getByGender(String sex){
		return studentRepository.findByGender(sex);
	}
	
	@RequestMapping("/getBySnoRange")
	public List<Student> getBySnoRange(int sno1,int sno2){
		return studentRepository.findBySnoRange(sno1,sno2);
	}
	
	//返回记录的总数
	@RequestMapping("/count")
	public Long count() {
		return studentRepository.count();
	}
	
	//删除
	@RequestMapping("/deleteBySno")
	public String deleteBySno(int sno) {
		studentRepository.deleteBySno(sno);
		return "删除成功";
	}
	
	@RequestMapping("/deleteByMacID")
	public String deleteByMacID(String macID) {
		studentRepository.deleteByName(macID);
		return "删除成功";
	}
	
	@RequestMapping("deleteAll")
	public String deleteAll() {
		studentRepository.deleteAll();
		return "删除成功";
	}
	
	
	//更新
	@RequestMapping("/updateName")
	public String updateNameBySno(String newName,int sno) {
		studentRepository.updateNameBySno(newName, sno);
		return "更新成功"; 
	}
	
	@RequestMapping("/updateSex")
	public String updateSexBySno(String newSex,int sno) {
		studentRepository.updateSexBySno(newSex, sno);
		return "更新成功"; 
	}
	
	
}
