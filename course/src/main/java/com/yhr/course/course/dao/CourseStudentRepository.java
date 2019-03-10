package com.yhr.course.course.dao;

import com.yhr.course.course.entity.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2019-01-09.
 */
public interface CourseStudentRepository extends JpaRepository<CourseStudent, Integer> {

    CourseStudent findByStudentIdAndCourseId(Integer studentId, Integer courseId);

    List<CourseStudent> findByStudentId(Integer studentId);
}
