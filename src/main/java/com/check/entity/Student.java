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
	@Column(unique = true, nullable = false)
	private int sno;
	//mac地址
	@Column(name = "mac_id", unique = true, nullable = false)
	private String macID;
	//姓名
	@Column(nullable = false)
	private String name;
	//性别
	@Column(nullable = false)
	private String gender;

	//班级
	@Column(nullable = false)
	private String team;
	//密码
	@Column(nullable = false)
	private String password;

	public String getMacID() {
		return macID;
	}

	public void setMacID(String macID) {
		this.macID = macID;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Student(int sno, String macID, String name, String gender, String team, String password) {
		this.sno = sno;
		this.macID = macID;
		this.name = name;
		this.gender = gender;
		this.team = team;
		this.password = password;
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


	public Student() {
	}


}
