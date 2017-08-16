package com.check.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

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
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

  
	
}
