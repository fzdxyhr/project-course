package com.yhr.course.course.dao;

import com.yhr.course.course.entity.CourseChapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2019/3/3.
 */
public interface CourseChapterRepository extends JpaRepository<CourseChapter, Integer> {

    List<CourseChapter> findByCourseId(Integer courseId);

}
