package com.check.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.check.dao.Teacher_ClassRepository;
import com.check.entity.Teacher_Class;

@RestController
@RequestMapping("/Teacher_Class")
public class Teacher_ClassController {
	
	@Autowired
	private Teacher_ClassRepository teacher_classRepository;
	
	//添加
	@RequestMapping(value="/add")
	public String add(@RequestBody Teacher_Class teacher_class) {
		if(teacher_classRepository.findByTnoAndCno(teacher_class.getTno(),teacher_class.getCno())!=null)
		{
			return "该记录已存在";
		}
		teacher_classRepository.save(teacher_class);	
		return "保存成功";
}
	
	@RequestMapping(value="findByTno")
	public List<Teacher_Class> FindByTno(int tno){
		return teacher_classRepository.findByTno(tno);
	}
	
	@RequestMapping(value="findByCno")
	public List<Teacher_Class> FindByCno(int cno){
		return teacher_classRepository.findByCno(cno);
	}
	
	@RequestMapping(value="findByTnoAndCno")
	public Teacher_Class FindByTnoAndCno(int tno,int cno){
		return teacher_classRepository.findByTnoAndCno(tno,cno);
	}
	
	@RequestMapping("/deleteByTno")
	public String DeleteByTno(int tno) {
		teacher_classRepository.deleteByTno(tno);
		return "删除成功";
	}
	
	@RequestMapping("/deleteByCno")
	public String DeleteByCno(int cno) {
		teacher_classRepository.deleteByCno(cno);
		return "删除成功";
	}
	
	@RequestMapping("/deleteByTnoAndCno")
	public String DeleteByTnoAndCno(int tno,int cno) {
		teacher_classRepository.deleteByTnoAndCno(tno,cno);
		return "删除成功";
	}
	
	@RequestMapping("/deleteByTnoAndCname")
	public String DeleteByTnoAndCname(int tno,String cname) {
		teacher_classRepository.deleteByTnoAndCname(tno,cname);
		return "删除成功";
	}
	
}
