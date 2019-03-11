package com.yhr.course.course.dao;

import com.yhr.course.course.entity.UserStudyProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yhr
 * @version latest
 * @date 2019/3/11
 */
public interface UserStudyProgressRepository extends JpaRepository<UserStudyProgress, Integer> {

    List<UserStudyProgress> findByCourseIdAndUserIdOrderByCreateTimeDesc(Integer courseId, Integer userId);
}
