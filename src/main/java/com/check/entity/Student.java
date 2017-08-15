package com.check.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;






/**
 * Created by yancychan on 17-6-28.
 */
@Entity
@Table(name="student")
public class Student {
	
	//主键
	@Id
	@GeneratedValue
	private int id;
	//学号
	@Column(unique=true,nullable=false)
	private int sno;
	//mac地址
	@Column(unique=true,nullable=false)
	private String macID;
	//姓名
	@Column(nullable=false)
	private String name;
	//性别
	@Column(nullable=false)
	private String gender;
	
	
	public String getMacID() {
		return macID;
	}
	public void setMacID(String macID) {
		this.macID = macID;
	}
	
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Student [macID=" + macID + ", sno=" + sno + ", name=" + name + ", gender=" + gender + "]";
	}
	
	public Student () {}
	public Student(String macID, int sno, String name, String gender) {
		super();
		this.macID = macID;
		this.sno = sno;
		this.name = name;
		this.gender = gender;
	}
	
	
	
	
	
}
