package com.check.dao;

import com.check.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by sccy on 2017/9/20/0020.
 */
@Transactional
public interface RecordRepository extends JpaRepository<Record, Long> {

    //通过学生学号返回其在所有已选课堂的所有签到记录
    List<Record> findBySno(int sno);
    //通过课程号和学号返回其所有的签到记录
    List<Record> findByCnoAndSnoOrderByNumber(int cno,int sno);
    //通过课程号和学号和第几次考勤获取签到记录
    Record findByCnoAndSnoAndNumber(int cno,int sno,int number);


    //通过学号和课程号和签到次数更改出勤记录
    @Modifying(clearAutomatically=true)
    @Query("update Record u set u.attendence=1 where u.cno=?1 and u.sno=?2 and u.number=?3")
    void updateAttend_times(int cno,int sno,int number);


    void deleteByCnoAndSno(int cno,int sno);//删除某个学生在某个课堂的所有签到记录
    void deleteBySno(int sno);//通过学号删除该学生的所有的签到记录
    void deleteByCno(int cno);//通过课程号删除该课程下的所有签到记录
}
