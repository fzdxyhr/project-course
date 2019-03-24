package com.yhr.course.course.dao;

import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.entity.Teach;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2019-01-10.
 */
public interface TeachRepository extends JpaRepository<Teach, Integer> {
}


