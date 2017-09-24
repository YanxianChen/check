package com.check.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//描述老师与课程之间的对应关系
@Entity
@Table(name="classroom")
public class ClassRoom {

	@Id
	@GeneratedValue
	private int id;
	@Column(nullable=false)
	private int cno;//课程号
	@Column(nullable=false)
	private String cname;//课程名
	@Column(nullable=false)
	private String place;//地点
	@Column(nullable=false)
	private String time;//时间
	@Column(nullable=false)
	private int capacity;//容量
	@Column(nullable = false)
	private int amount;//学生人数
	@Column(nullable=false)
	private int number;//课堂编号
	@Column(nullable=false)
	private int total;//考勤总次数

	public String getPlace() {return place;}
	public void setPlace(String place) {this.place = place;}

	public String getTime() {return time;}
	public void setTime(String time) {this.time = time;}

	public int getAmount() {return amount;}
	public void setAmount(int amount) {this.amount = amount;}

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

	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getNumber() {return number;}
	public void setNumber(int number) {this.number = number;}

	public int getTotal() {return total;}
	public void setTotal(int total) {this.total = total;}

	public ClassRoom(int cno, String cname, String place, String time, int capacity, int number) {
		this.cno = cno;
		this.cname = cname;
		this.place = place;
		this.time = time;
		this.capacity = capacity;
		this.number = number;
	}

	public ClassRoom() {

	}
}
