package com.yhr.course.course.controller;

import com.yhr.course.course.service.CourseCommentService;
import com.yhr.course.course.service.QuestionService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.CourseCommentVo;
import com.yhr.course.course.vo.QuestionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2019-01-10.
 */

@RestController
@RequestMapping("/v1")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/course_questions", method = RequestMethod.POST)
    public QuestionVo create(@RequestBody QuestionVo questionVo) {
        return questionService.create(questionVo);
    }

    @RequestMapping(value = "/course_questions", method = RequestMethod.GET)
    public PagerHelper<QuestionVo> list(@RequestParam(value = "key", required = false) String key
            , @RequestParam(value = "course_id", required = false) Integer courseId
            , @RequestParam(value = "page_no", defaultValue = "1") Integer pageNo
            , @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) throws Exception {
        return questionService.list(key, null, courseId, pageNo, pageSize);
    }

    @RequestMapping(value = "/course_questions/{id}", method = RequestMethod.GET)
    public QuestionVo get(@PathVariable("id") Integer id) throws Exception {
        return questionService.get(id);
    }

}