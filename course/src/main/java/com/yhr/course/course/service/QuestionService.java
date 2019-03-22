package com.yhr.course.course.service;

import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.CourseCommentVo;
import com.yhr.course.course.vo.QuestionVo;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Administrator on 2019/3/8.
 */
public interface QuestionService {

    PagerHelper<QuestionVo> list(String key, Integer userId, Integer courseId, Integer pageNo, Integer pageSize);

    QuestionVo create(QuestionVo questionVo);

    QuestionVo get(Integer id) throws Exception;

}
