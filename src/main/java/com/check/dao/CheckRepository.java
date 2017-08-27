package com.check.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.check.entity.Check;


@Transactional
public interface CheckRepository extends JpaRepository<Check, Long> {

	List<Check> findBySno(int sno);//通过学生学号返回其所有的签到记录
	List<Check> findBySname(String sname);
	List<Check> findByCno(int cno);//通过课程号返回其所有的签到记录
	List<Check> findByCname(String cname);
	
	Check findBySnoAndCno(int sno,int cno);//返回指定学号的学生在指定课程号的签到记录
	Check findBySnameAndCname(String sname,String cname);
	Check findBySnoAndCname(int sno,String cname);
	Check findBySnameAndCno(String sname,int cno);
	
	
	
	//重置本次出勤记录
	@Modifying
	@Query("update Check u set u.attendence=0 where u.cno=?1")
	void resetAttendence(int cno);
	
	//更新指定学生在指定课堂的本次出勤记录
	@Modifying
	@Query("update Check u set u.attendence=1 where u.sno=?1 and u.cno=?2")
	void updateAttendenceBySnoAndCno(int sno,int cno);
	
	//置零指定学生在指定课堂的当前出勤记录
	@Modifying
	@Query("update Check u set u.attendence=0 where u.sno=?1 and u.cno=?2")
	void resetAttendenceBySnoAndCno(int sno,int cno);
	
	//置零指定学生在指定课堂的总出勤次数
	@Modifying
	@Query("update Check u set u.attend_times=0 where u.sno=?1 and u.cno=?2")
	void resetAttend_timesBySnoAndCno(int sno,int cno);

	//批量更新总出勤次数，使其加一
	@Modifying
	@Query("update Check u set u.attend_times=attend_times+1 where u.attendence=1 and u.cno=?1")
	void updateAttend_times(int cno);
	
	//更新指定学生在指定课堂的总出勤次数
	@Modifying
	@Query("update Check u set u.attend_times=attend_times+1 where u.sno=?1 and u.cno=?2")
	void updateAttend_timesBySnoAndCno(int sno,int cno);
	
	//更新日期
	@Modifying
	@Query("update Check u set u.time=?1 where u.sno=?2 and u.cno=?3")
	void updateTimeBySnoAndCno(String time,int sno,int cno);

	void deleteBySno(int sno);//通过学号删除该学生的所有的签到记录
	void deleteByCno(int cno);//通过课程号删除该课程下的所有签到记录
	void deleteBySnoAndCno(int sno,int cno);//删除某个学生在指定课堂的签到记录
}
