package com.check.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="attend")
public class Check {
	@Id
	@GeneratedValue
	private int id;
	//课程号
	@Column(nullable=false)
	private int cno;
	//课程名
	@Column(nullable=false)
	private String cname;
	//学号
	@Column(nullable=false)
	private int sno;
	//学生姓名
	@Column(nullable=false)
	private String sname;
	//课堂号
	@Column(nullable = false)
	private int classroom;
	//出勤总次数
	@Column(nullable = false)
	private int attend_times;
	//考勤总次数
	@Column(nullable=false)
	private int total;
	//签到记录
	@OneToMany
	private List<Record> records = new ArrayList<>();

	public Check(int cno, String cname, int sno, String sname, int classroom, int attend_times, int total) {
		this.cno = cno;
		this.cname = cname;
		this.sno = sno;
		this.sname = sname;
		this.classroom = classroom;
		this.attend_times = attend_times;
		this.total = total;
	}
	public Check() {}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getClassroom() {
		return classroom;
	}

	public void setClassroom(int classroom) {
		this.classroom = classroom;
	}

	public int getAttend_times() {
		return attend_times;
	}

	public void setAttend_times(int attend_times) {
		this.attend_times = attend_times;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}
}
