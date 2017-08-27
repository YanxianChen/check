package com.check.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="course")
public class Course {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="course_id",unique=true,nullable=false)
	private int courseID;//课程号
	
	@Column(name="course_name",nullable=false)
	private String courseName;//课程名
	
	@Column(nullable=false)
	private int period;//课时
	
	@Column(nullable=false)
	private int capacity;//容量
	
	@Column(nullable=false)
	private String place;//地点
	
	@Column(nullable=false)
	private String time;//时间
	
	public Course() {}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getCourseID() {
		return courseID;
	}


	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}



	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	
	@Override
	public String toString() {
		return "Course [courseID=" + courseID + ", courseName=" + courseName + ", period=" + period + ", capacity="
				+ capacity + ", place=" + place + ", time=" + time + "]";
	}
	
	
}
