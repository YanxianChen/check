package com.check.dao;

import com.check.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	Teacher findByTno(int tno);//工号查询
	Teacher findByEmail(String email);
	List<Teacher> findByName(String name);//姓名查询
	List<Teacher> findByGender(String sex);//性别查询
	List<Teacher> findAll();//查询所有记录

 	@Modifying(clearAutomatically=true)
	@Query(value="update Teacher u set u.name= :newName where u.tno= :Tno")
	 void updateNameByTno(@Param("newName") String newName, @Param("Tno") int Tno);
	//通过工号改变名字


	@Modifying(clearAutomatically=true)
	@Query(value="update Teacher u set u.gender= :newSex where u.tno= :Tno")
	 void updateSexByTno(@Param("newSex") String newSex, @Param("Tno") int Tno); 
	//通过工号改变性别

	@Modifying(clearAutomatically=true)
	@Query(value="update Teacher u set u.password= :newPassword where u.tno= :Tno")
	 void updatePasswordByTno(@Param("newPassword") String newPassword, @Param("Tno") int Tno); 
	//通过工号改变密码

	@Modifying(clearAutomatically=true)
	@Query(value="update Teacher u set u.tno= :newTno where u.email= :email")
	void updateTnoByEmail(@Param("newTno") int  newTno, @Param("email") String email);
	//通过邮箱修改工号

	@Modifying(clearAutomatically=true)
	@Query(value="update Teacher u set u.name= :newName where u.email= :email")
	void updateNameByEmail(@Param("newName") String  newName, @Param("email") String email);
	//通过邮箱修改姓名

	@Modifying(clearAutomatically=true)
	@Query(value="update Teacher u set u.gender= :newSex where u.email= :email")
	void updateGenderByEmail(@Param("newSex") String  newSex, @Param("email") String email);
	//通过邮箱修改性别

	@Modifying(clearAutomatically=true)
	@Query(value="update Teacher u set u.password= :newPassword where u.email= :email")
	void updatePasswordByEmail(@Param("newPassword") String  newPassword, @Param("email") String email);
	//通过邮箱修改密码


    void deleteByTno(int tno);  //通过工号删除
       
}
