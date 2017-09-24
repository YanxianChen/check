package com.check.dao;

import com.check.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {

	List<ClassRoom> findByCno(int cno);
	List<ClassRoom> findByCname(String cname);
	
	ClassRoom findByCnoAndNumber(int cno, int num);
	ClassRoom findByCnameAndNumber(String cname, int num);


	@Modifying(clearAutomatically=true)
	@Query(value="update ClassRoom u set u.place= :newPlace where u.cno= :cno and u.number= :number")
	void updatePlaceByID(@Param("newPlace") String Place, @Param("cno") int cno,@Param("number") int number);

	@Modifying(clearAutomatically=true)
	@Query(value="update ClassRoom u set u.time= :newTime where u.cno= :cno and u.number= :number")
	void updateTimeByID(@Param("newTime") String time, @Param("cno") int cno,@Param("number") int number);

	@Modifying(clearAutomatically=true)
	@Query("update ClassRoom u set u.total=total+1 where u.cno=?1 and u.number=?2")
	void updateTotalByID(int cno,int number);

	@Modifying(clearAutomatically=true)
	@Query("update ClassRoom u set u.amount=amount+1 where u.cno=?1 and u.number=?2")
	void updateAmountByID(int cno,int number);

	@Modifying(clearAutomatically=true)
	@Query(value="update ClassRoom u set u.capacity= :newCapacity where u.cno= :cno and u.number= :number")
	void updateCapacityByID(@Param("newCapacity") int capacity, @Param("cno") int cno,@Param("number") int number);

	@Modifying(clearAutomatically=true)
	@Query(value="update ClassRoom u set u.number= :newNumber where u.cno= :cno and u.number= :number")
	void updateNumberByID(@Param("newNumber") int capacity, @Param("cno") int cno,@Param("number") int number);


	void deleteByCno(int cno);
	void deleteByCname(String cname);
	void deleteByCnoAndNumber(int cno,int num);

	
}
