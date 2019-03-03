package com.yhr.course.course.service;

import com.yhr.course.course.entity.CourseChapter;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.utils.PagerHelper;

/**
 * Created by Administrator on 2019/3/3.
 */
public interface CourseChapterService {

    PagerHelper<CourseChapter> list(String key, Integer pageNo, Integer pageSize);

    CourseChapter create(CourseChapter courseChapter);

    CourseChapter update(Integer id, CourseChapter courseChapter) throws Exception;

    void delete(Integer id) throws Exception;

    CourseChapter get(Integer id) throws Exception;
}
