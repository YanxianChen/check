package com.check.dao;

import java.sql.Time;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.check.entity.Course;

@Transactional
public interface CourseRepository extends JpaRepository<Course, Long>{
	Course findByCourseID(int courseID);//课程号查询
	Course findByCourseName(String courseName);//课程名查询
	List<Course> findAll();//查询所有记录

	@Modifying 
	@Query(value="update Course u set u.courseName= :newName where u.courseID= :ID")
	 void updateNameByID(@Param("newName") String newName, @Param("ID") int ID);
	
	@Modifying 
	@Query(value="update Course u set u.courseID= :newID where u.courseName= :Name")
	 void updateIDByName(@Param("newID") int newID,@Param("Name") String Name);
	
	@Modifying 
	@Query(value="update Course u set u.week= :newWeek where u.courseID= :ID")
	 void updateWeekByCourseID(@Param("newWeek") String newWeek,@Param("ID") int ID);
	
	@Modifying 
	@Query(value="update Course u set u.start_time= :newTime where u.courseID= :ID")
	 void updateStartTimeByCourseID(@Param("newTime") Time newTime,@Param("ID") int ID);
	
	@Modifying 
	@Query(value="update Course u set u.end_time= :newTime where u.courseID= :ID")
	 void updateEndTimeByCourseID(@Param("newTime") Time newTime,@Param("ID") int ID);
	
	@Modifying
	@Query(value="update Course u set u.place= :newPlace where u.courseID= :ID")
	void updatePlaceByCourseID(@Param("newPlace") String Place,@Param("ID") int ID);
	
	
	void deleteByCourseName(String name);   
    void deleteByCourseID(int ID);  

}
