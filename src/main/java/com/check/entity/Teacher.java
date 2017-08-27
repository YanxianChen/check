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
@Table(name="teacher")
public class Teacher {

	@Id
	@GeneratedValue
	private int id;
	//工号
	@Column(unique=true,nullable=false)
	private int tno;
	//姓名
	@Column(nullable=false)	
	private String name;
	//性别
	@Column(nullable=false)	
    private String gender;
	//密码
	@Column(nullable=false)
	private String password;
	
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public Teacher() {}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
		public String toString() {
			return "Teacher [tno=" + tno + ", name=" + name + ", gender=" + gender + ", password=" + password + "]";
		}
  
	
}
