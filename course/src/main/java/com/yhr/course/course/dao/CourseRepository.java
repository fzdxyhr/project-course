package com.yhr.course.course.dao;

import com.yhr.course.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2019-01-09.
 */
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findByIdIn(List<Integer> ids);
}
