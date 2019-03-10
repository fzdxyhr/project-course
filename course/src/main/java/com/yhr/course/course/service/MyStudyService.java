package com.yhr.course.course.service;

import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.CourseCommentVo;
import com.yhr.course.course.vo.CourseVo;
import com.yhr.course.course.vo.QuestionVo;

import java.util.List;

/**
 * Created by Administrator on 2019/3/8.
 */
public interface MyStudyService {

    PagerHelper<CourseVo> findMyCourse(Integer pageNo, Integer pageSize);

    PagerHelper<CourseCommentVo> listComments(Integer pageNo, Integer pageSize);

    PagerHelper<QuestionVo> listQuestions(Integer pageNo, Integer pageSize);
}
