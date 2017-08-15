package com.check.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.check.entity.Teacher_Class;

@Transactional
public interface Teacher_ClassRepository extends JpaRepository<Teacher_Class, Long> {

	List<Teacher_Class> findByTno(int tno);//通过工号返回对应关系
	List<Teacher_Class> findByCno(int cno);//通过课程号返回对应关系
	List<Teacher_Class> findByTname(String tname);
	List<Teacher_Class> findByCname(String cname);
	
	Teacher_Class findByTnoAndCno(int tno,int cno);//通过课程号和工号组合查询
	Teacher_Class findByTnameAndCname(String tname,String cname);
	Teacher_Class findByTnoAndCname(int tno,String cname);
	Teacher_Class findByTnameAndCno(String tname,int cno);
	
	
	void deleteByTno(int tno);
	void deleteByCno(int cno);
	void deleteByTname(String tname);
	void deleteByCname(String cname);
	void deleteByTnoAndCno(int tno,int cno);
	void deleteByTnameAndCname(String tname,int cname);
	void deleteByTnoAndCname(int tno,String cname);
	void deleteByTnameAndCno(String tname,int cno);
	
}
