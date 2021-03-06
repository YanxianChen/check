package com.check.entity;


import javax.persistence.Column;
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
	@Column(nullable=false)
	private int tno;
	//课程号
	@Column(nullable=false)
	private int cno;
	//老师姓名
	@Column(nullable=false)
	private String tname;
	//课程名
	@Column(nullable=false)
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
	
	
	@Override
	public String toString() {
		return "Teacher_Class [tno=" + tno + ", cno=" + cno + ", tname=" + tname + ", cname=" + cname + "]";
	}
}
