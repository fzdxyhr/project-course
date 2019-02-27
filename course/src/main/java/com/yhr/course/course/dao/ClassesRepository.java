package com.yhr.course.course.dao;

import com.yhr.course.course.entity.Classes;
import com.yhr.course.course.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2019-01-10.
 */
public interface ClassesRepository extends JpaRepository<Classes, Integer> {
}


