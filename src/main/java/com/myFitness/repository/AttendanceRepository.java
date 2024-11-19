package com.myFitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myFitness.entity.Attendance;

import jakarta.transaction.Transactional;

@Repository
@EnableJpaRepositories
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	

	@Query("UPDATE Attendance a SET a.outTime = :outTime WHERE a.attendanceId = :attendanceId")
	@Transactional
	@Modifying
	int insertOutTime(@Param("outTime") String time,@Param("attendanceId") Long attendanceId);

	@Query("SELECT a FROM Attendance a WHERE date = :date")
	List<Attendance> getByDate(String date);

	
	@Query("SELECT a FROM Attendance a WHERE a.date BETWEEN :startDate AND :endDate ")
	List<Attendance> customeRangeRecord(@Param("startDate") String startDate, @Param("endDate")String endDate);
	
}
