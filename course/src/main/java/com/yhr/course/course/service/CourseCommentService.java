package com.yhr.course.course.service;

import com.yhr.course.course.entity.Course;
import com.yhr.course.course.entity.CourseComment;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.CourseCommentVo;
import com.yhr.course.course.vo.CourseVo;

/**
 * Created by Administrator on 2019/3/7.
 */
public interface CourseCommentService {

    PagerHelper<CourseCommentVo> list(String key, Integer userId, Integer courseId, Integer pageNo, Integer pageSize);

    CourseCommentVo create(CourseCommentVo courseComment);
}
