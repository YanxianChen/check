package com.check.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.check.dao.TeacherRepository;
import com.check.entity.Course;
import com.check.entity.Teacher;



/**
 * Created by yancychan on 17-6-28.
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	//添加
	@RequestMapping(value="/add")
	public Teacher add(@RequestBody Teacher teacher) {
		return teacherRepository.save(teacher);
	}
	
	
	//查询
	@RequestMapping("/getByTno")
	public Teacher getBySno(int tno) {
		return teacherRepository.findByTno(tno);
	}
	
	@RequestMapping("/getByName")
	public List<Teacher>  getByName(String name) {
		return teacherRepository.findByName(name);
	}
	
	
	@RequestMapping("/getAll")
	public List<Teacher> getAll(){
		return teacherRepository.findAll();
	}
	
	@RequestMapping("/getByGender")
	public List<Teacher> getByGender(String sex){
		return teacherRepository.findByGender(sex);
	}

	
	@RequestMapping("/count")
	public Long count() {
		return teacherRepository.count();
	}
	
	//删除
	@RequestMapping("/deleteByTno")
	public String deleteByTno(int tno) {
		teacherRepository.deleteByTno(tno);
		return "删除成功";
	}
	
	@RequestMapping("/deleteByTname")
	public String deleteByTname(String name) {
		//List<Teacher> teachers = teacherRepository.findByName(name);
		teacherRepository.deleteByName(name);
		return "删除成功";
	}
	
	@RequestMapping("deleteAll")
	public String deleteAll() {
		teacherRepository.deleteAll();
		return "删除成功";
	}
	

	//更新
	@RequestMapping("/updateName")
	public String updateNameByTno(String newName,int tno) {
		teacherRepository.updateNameByTno(newName, tno);
		return "更新成功"; 
	}
	
	@RequestMapping("/updateTno")
	public String updateTnoByName(int newTno,String name) {
		teacherRepository.updateTnoByName(newTno, name);
		return "更新成功"; 
	}
	
	@RequestMapping("/updateSex")
	public String updateSexByTno(String newSex,int tno) {
		teacherRepository.updateSexByTno(newSex, tno);
		return "更新成功"; 
	}
	
	
	
}
