package com.check.dao;


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
	List<Course> findByCourseName(String courseName);//课程名查询
	List<Course> findAll();//查询所有记录

	@Modifying 
	@Query(value="update Course u set u.courseName= :newName where u.courseID= :ID")
	 void updateNameByID(@Param("newName") String newName, @Param("ID") int ID);
	
	@Modifying
	@Query(value="update Course u set u.place= :newPlace where u.courseID= :ID")
	void updatePlaceByID(@Param("newPlace") String Place,@Param("ID") int ID);
	
	@Modifying
	@Query(value="update Course u set u.time= :newTime where u.courseID= :ID")
	void updateTimeByID(@Param("newTime") String time,@Param("ID") int ID);
	
	@Modifying
	@Query(value="update Course u set u.period= :newPeriod where u.courseID= :ID")
	void updatePeriodByID(@Param("newPeriod") int Period,@Param("ID") int ID);
	
	@Modifying
	@Query(value="update Course u set u.capacity= :newCapacity where u.courseID= :ID")
	void updateCapacityByID(@Param("newCapacity") int capacity,@Param("ID") int ID);
	

    void deleteByCourseID(int ID);  

}
