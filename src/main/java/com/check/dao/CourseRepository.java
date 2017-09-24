package com.check.dao;


import com.check.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CourseRepository extends JpaRepository<Course, Long>{
	Course findByCourseID(int courseID);//课程号查询
	List<Course> findByCourseName(String courseName);//课程名查询
	List<Course> findByTname(String tname);//老师名查询
	List<Course> findAll();//查询所有记录

	@Modifying(clearAutomatically=true)
	@Query(value="update Course u set u.courseName= :newName where u.courseID= :ID")
	 void updateNameByID(@Param("newName") String newName, @Param("ID") int ID);

	@Modifying(clearAutomatically=true)
	@Query(value="update Course u set u.period= :newPeriod where u.courseID= :ID")
	void updatePeriodByID(@Param("newPeriod") int Period,@Param("ID") int ID);

    void deleteByCourseID(int ID);  

}
