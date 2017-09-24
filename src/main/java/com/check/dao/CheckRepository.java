package com.check.dao;

import com.check.entity.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface CheckRepository extends JpaRepository<Check, Long> {

	//通过学生学号返回其在所有已选课堂的所有签到记录以及统计信息
	List<Check> findBySno(int sno);
	//通过课程号和课堂号返回该课堂下所有学生的所有签到记录以及统计信息
	List<Check> findByCnoAndClassroom(int cno,int classroom);
	//通过课程号和学号返回其所有的签到记录以及统计信息
	Check findByCnoAndSno(int cno,int sno);




	//查询某个课程号和课堂号下所有学生的学号
	@Query("select u.sno from Check u where u.cno=?1 and u.classroom=?2")
	List<Integer> findSnoByCnoAndClassRoom(int cno,int classroom);

	//通过学号和课程号增加出勤次数
	@Modifying(clearAutomatically=true)
	@Query("update Check u set u.attend_times=attend_times+1 where u.sno=?1 and u.cno=?2")
	void updateAttend_times(int sno,int cno);

	//通过课程号和课堂号增加考勤次数
	@Modifying(clearAutomatically=true)
	@Query("update Check u set u.total=total+1 where u.cno=?1 and u.classroom=?2")
	void updateTotal(int cno,int classroom);
	


	void deleteBySno(int sno);//通过学号删除该学生的所有的签到记录
	void deleteByCno(int cno);//通过课程号删除该课程下的所有签到记录
	void deleteBySnoAndCno(int sno,int cno);//删除某个学生在指定课堂的签到记录
}
