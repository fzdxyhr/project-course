package com.yhr.course.course.dao;

import com.yhr.course.course.entity.CourseComment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2019/3/7.
 */
public interface CourseCommentRepository extends JpaRepository<CourseComment, Integer> {
}
