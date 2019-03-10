package com.yhr.course.course.controller;

import com.yhr.course.course.entity.CourseComment;
import com.yhr.course.course.service.CourseChapterService;
import com.yhr.course.course.service.CourseCommentService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.CourseChapterVo;
import com.yhr.course.course.vo.CourseCommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2019-01-10.
 */

@RestController
@RequestMapping("/v1")
public class CourseCommentController {

    @Autowired
    private CourseCommentService courseCommentService;

    @RequestMapping(value = "/course_comments", method = RequestMethod.POST)
    public CourseCommentVo create(@RequestBody CourseCommentVo courseComment) {
        return courseCommentService.create(courseComment);
    }

    @RequestMapping(value = "/course_comments", method = RequestMethod.GET)
    public PagerHelper<CourseCommentVo> list(@RequestParam(value = "key", required = false) String key
            , @RequestParam(value = "course_id", required = false) Integer courseId
            , @RequestParam(value = "page_no", defaultValue = "1") Integer pageNo
            , @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) throws Exception {
        return courseCommentService.list(key, null, courseId, pageNo, pageSize);
    }

}