package com.check.entity;

import javax.persistence.*;

/**
 * Created by sccy on 2017/9/20/0020.
 */
@Entity
@Table(name="record")
public class Record {

    @Id
    @GeneratedValue
    private int id;
    //课程号
    @Column(nullable=false)
    private int cno;
    //学号
    @Column(nullable=false)
    private int sno;
    //本次出勤记录
    @Column(nullable=false)
    private int attendence;
    //签到时间
    @Column(nullable=false)
    private String time;
    //第几次考勤
    @Column(nullable = false)
    private int number;


    public Record() {
    }

    public Record(int cno, int sno,int attendence, String time, int number) {
        this.cno = cno;
        this.sno = sno;
        this.attendence = attendence;
        this.time = time;
        this.number = number;
    }

    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public int getAttendence() {
        return attendence;
    }

    public void setAttendence(int attendence) {
        this.attendence = attendence;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
