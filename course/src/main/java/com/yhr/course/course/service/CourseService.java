package com.yhr.course.course.service;

import com.yhr.course.course.entity.Course;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.utils.PagerHelper;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2019-01-09.
 */
public interface CourseService {

    PagerHelper<Course> list(String key, Integer pageNo, Integer pageSize);

    Course create(Course course);

    Course update(Integer id, Course course) throws Exception;

    void delete(Integer id) throws Exception;

    Course get(Integer id) throws Exception;

    String upload(MultipartFile multipartFile) throws Exception;

    public void downloadImage(String relativePath, HttpServletResponse response) throws Exception;
}
