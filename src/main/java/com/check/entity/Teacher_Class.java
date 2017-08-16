package com.check.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//描述老师与课程之间的对应关系
@Entity
@Table(name="teacher_class")
public class Teacher_Class {

	@Id
	@GeneratedValue
	private int id;
	//老师工号
	private int tno;
	//课程号
	private int cno;
	//老师姓名
	private String tname;
	//课程名
	private String cname;


	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public int getCno() {
	return cno;
    }

	public void setCno(int cno) {
	this.cno = cno;
    }
	
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
}
