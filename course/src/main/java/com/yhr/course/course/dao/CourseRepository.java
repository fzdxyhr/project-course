package com.yhr.course.course.dao;

import com.yhr.course.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2019-01-09.
 */
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
