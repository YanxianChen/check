package com.check.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="teacher")
public class Teacher {

	@Id
	@GeneratedValue
	private int id;
	//工号
	@Column(unique=true)
	private int tno;
	//姓名
	@Column()
	private String name;
	//性别
	@Column()
    private String gender;
	//密码
	@Column(nullable=false)
	private String password;
	//邮箱
	@Column(nullable=false)
	private String email;
	//课程
	@OneToMany(fetch = FetchType.EAGER)
	private List<Course> courses = new ArrayList<>();

	@Override
	public String toString() {
		return "[id:"+id+","+"name:"+name+",gender:"+gender+",password:"+password+",email:"+email+",courses:"
				+courses+"]";
	}

	public List<Course> getCourses() {return courses;}
	public void setCourses(List<Course> courses) {this.courses = courses;}
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
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
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}

	public Teacher(int tno, String name, String gender, String password, String email, List<Course> courses) {
		this.tno = tno;
		this.name = name;
		this.gender = gender;
		this.password = password;
		this.email = email;
		this.courses = courses;
	}



	public Teacher() {}
}
