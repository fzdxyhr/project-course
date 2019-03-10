package com.yhr.course.course.controller;

import com.yhr.course.course.entity.CourseComment;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.service.MyStudyService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.CourseCommentVo;
import com.yhr.course.course.vo.CourseVo;
import com.yhr.course.course.vo.QuestionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/3/8.
 */

@RestController
@RequestMapping("/v1/mine")
public class MystudyController {

    @Autowired
    private MyStudyService myStudyService;

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public PagerHelper<CourseVo> list(@RequestParam(value = "page_no", defaultValue = "1") Integer pageNo
            , @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) {
        return myStudyService.findMyCourse(pageNo, pageSize);
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public PagerHelper<CourseCommentVo> listComments(@RequestParam(value = "page_no", defaultValue = "1") Integer pageNo
            , @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) {
        return myStudyService.listComments(pageNo, pageSize);
    }

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public PagerHelper<QuestionVo> listQuestions(@RequestParam(value = "page_no", defaultValue = "1") Integer pageNo
            , @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) {
        return myStudyService.listQuestions(pageNo, pageSize);
    }
}
