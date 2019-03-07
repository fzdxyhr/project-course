package com.yhr.course.course.service;

import com.yhr.course.course.entity.Course;
import com.yhr.course.course.entity.CourseStudent;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.CourseVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2019-01-09.
 */
public interface CourseService {

    PagerHelper<CourseVo> list(String key, Integer pageNo, Integer pageSize);

    Course create(Course course);

    Course update(Integer id, Course course) throws Exception;

    void delete(Integer id) throws Exception;

    CourseVo get(Integer id) throws Exception;

    void study(Integer id) throws Exception;

    CourseStudent getStudy(Integer id) throws Exception;

    String upload(MultipartFile multipartFile) throws Exception;

    String uploadFile(MultipartFile multipartFile) throws Exception;

    public void downloadImage(String fileName, HttpServletResponse response) throws Exception;

    public void downloadFile(String fileName, HttpServletResponse response) throws Exception;
}
