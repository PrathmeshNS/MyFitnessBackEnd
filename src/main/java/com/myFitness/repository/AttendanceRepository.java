package com.myFitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myFitness.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	@Query("UPDATE Attendance a SET a.outTime = :outTime WHERE a.attendanceId = :attendanceId")
	Boolean insertOutTime(@Param("outTime") String time,@Param("attendanceId") Long attendanceId);

}
