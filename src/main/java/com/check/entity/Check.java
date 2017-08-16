package com.check.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	//签到日期
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	//本次出勤记录
	@Column(nullable=false)
	private int attendence;
	//出勤总次数
	@Column(nullable=false)
	private int attend_times;
	

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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getAttendence() {
		return attendence;
	}
	public void setAttendence(int attendence) {
		this.attendence = attendence;
	}
	public int getAttend_times() {
		return attend_times;
	}
	public void setAttend_times(int attend_times) {
		this.attend_times = attend_times;
	}
	@Override
	public String toString() {
		return "Check [cno=" + cno + ", cname=" + cname + ", sno=" + sno + ", sname=" + sname + ", date=" + date
				+ ", attendence=" + attendence + ", attend_times=" + attend_times + "]";
	}
}
