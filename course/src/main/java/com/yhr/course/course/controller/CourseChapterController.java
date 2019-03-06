package com.yhr.course.course.controller;

import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.service.CourseChapterService;
import com.yhr.course.course.service.TagService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.CourseChapterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2019-01-10.
 */

@RestController
@RequestMapping("/v1")
public class CourseChapterController {

    @Autowired
    private CourseChapterService courseChapterService;

    @RequestMapping(value = "/course_chapters", method = RequestMethod.POST)
    public CourseChapterVo create(@RequestBody CourseChapterVo courseChapterVo) {
        return courseChapterService.create(courseChapterVo);
    }

    @RequestMapping(value = "/course_chapters/{id}", method = RequestMethod.PUT)
    public CourseChapterVo update(@PathVariable("id") Integer id, @RequestBody CourseChapterVo courseChapterVo) throws Exception {
        return courseChapterService.update(id, courseChapterVo);
    }

    @RequestMapping(value = "/course_chapters/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) throws Exception {
        courseChapterService.delete(id);
    }

}