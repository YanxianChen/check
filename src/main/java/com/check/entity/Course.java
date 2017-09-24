package com.check.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

	@Column(nullable = false)
	private int tno;//工号

	@Column(nullable = false)
	private String tname;//教师姓名

	@OneToMany(fetch = FetchType.EAGER)
	private List<ClassRoom> classrooms = new ArrayList<>();//课堂


	public Course() {}

	public Course(int courseID, String courseName, int period, int tno, String tname) {
		this.courseID = courseID;
		this.courseName = courseName;
		this.period = period;
		this.tno = tno;
		this.tname = tname;

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

	public List<ClassRoom> getClassrooms() {return classrooms;}

	public void setClassrooms(List<ClassRoom> classrooms) {this.classrooms = classrooms;}

	public int getTno() {
		return tno;
	}

	public void setTno(int tno) {
		this.tno = tno;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}



	@Override
	public String toString() {
		return "Course{" +
				"courseID=" + courseID +
				", courseName='" + courseName + '\'' +
				", period=" + period +
				", tno=" + tno +
				", tname='" + tname + '\'' +
				", classrooms=" + classrooms +
				'}';
	}
}
