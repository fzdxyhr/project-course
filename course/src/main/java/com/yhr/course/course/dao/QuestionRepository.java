package com.yhr.course.course.dao;

import com.yhr.course.course.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2019-01-09.
 */
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
