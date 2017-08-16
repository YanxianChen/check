package com.check.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.check.entity.Student;
//学生数据表操作接口
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {

	/**
	 * 查询操作
	 */
	Student findBySno(int sno);//通过学号查询学生
	Student findByName(String name);//通过姓名查询
	Student findByMacID(String MacID);//通过mac地址查询
	Student findByNameAndSno(String name,int sno);//通过姓名和学号组合查询
	List<Student> findAll();//查询所有记录
	List<Student> findByGender(String sex);//查询特定性别的所有学生
	
	
	@Query(value="select macID,sno,name,gender from Student s where s.sno>?1 and s.sno<?2")
	List<Student> findBySnoRange(int sno1,int sno2);//查询在一定学号范围的学生
	
	/**
	 * 更新操作
	 */
	@Modifying 
	@Query(value="update Student u set u.name= :newName where u.sno= :Sno")
	 void updateNameBySno(@Param("newName") String newName, @Param("Sno") int Sno);
	//通过学号更新姓名
	
	@Modifying 
	@Query(value="update Student u set u.gender= :newSex where u.sno= :Sno")
	 void updateSexBySno(@Param("newSex") String newSex, @Param("Sno") int Sno); 
	//通过学号更新性别
	
	/**
	 * 删除操作
	 */
  
    void deleteByName(String name);//通过名字删除学生
    void deleteBySno(int sno);//通过学号删除学生
    void deleteByMacID(String macID);//通过mac地址删除

}
