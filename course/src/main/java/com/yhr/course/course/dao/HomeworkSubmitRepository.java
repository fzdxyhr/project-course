package com.yhr.course.course.dao;

import com.yhr.course.course.entity.HomeworkSubmit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2019/3/14.
 */
public interface HomeworkSubmitRepository extends JpaRepository<HomeworkSubmit, Integer> {

    List<HomeworkSubmit> findByHomeworkId(Integer homeworkId);

    HomeworkSubmit findByHomeworkIdAndUserId(Integer homeworkId, Integer userId);

    List<HomeworkSubmit> findByHomeworkIdAndUserIdIn(Integer homeworkId, List<Integer> userIds);
}
