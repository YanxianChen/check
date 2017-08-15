package com.check.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.check.entity.Teacher;

@Transactional
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	Teacher findByTno(int tno);//工号查询
	List<Teacher> findByName(String name);//姓名查询
	List<Teacher> findByGender(String sex);//性别查询你
 	@Modifying 
	@Query(value="update Teacher u set u.name= :newName where u.tno= :Tno")
	 void updateNameByTno(@Param("newName") String newName, @Param("Tno") int Tno);
	//通过工号改变名字
	
	@Modifying 
	@Query(value="update Teacher u set u.tno= :newTno where u.name= :name")
	 void updateTnoByName(@Param("newTno") int newTno, @Param("name") String name);
	//通过名字改变工号
	
	@Modifying 
	@Query(value="update Teacher u set u.gender= :newSex where u.tno= :Tno")
	 void updateSexByTno(@Param("newSex") String newSex, @Param("Tno") int Tno); 
	//通过工号改变性别
	
	
	
	void deleteByName(String name);//通过名字删除   
    void deleteByTno(int tno);  //通过工号删除
       
}
