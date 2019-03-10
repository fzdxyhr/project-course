package com.yhr.course.course.dao;

import com.yhr.course.course.entity.Homework;
import com.yhr.course.course.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2019-01-10.
 */
public interface HomeworkRepository extends JpaRepository<Homework, Integer> {
}


