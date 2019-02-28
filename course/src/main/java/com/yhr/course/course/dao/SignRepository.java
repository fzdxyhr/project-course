package com.yhr.course.course.dao;

import com.yhr.course.course.entity.Sign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Administrator on 2019-01-09.
 */
public interface SignRepository extends JpaRepository<Sign, Integer> {

    @Query(value = "SELECT a.* from s_sign a where student_id=:studentId and teacher_id=:teacherId and DATE_FORMAT(create_time,'%Y-%m-%d')=:dateTime", nativeQuery = true)
    Sign findByStudentIdAndTeacherIdAndCreateTime(@Param("studentId") Integer studentId,@Param("teacherId") Integer teacherId,@Param("dateTime") String dateTime);

}
