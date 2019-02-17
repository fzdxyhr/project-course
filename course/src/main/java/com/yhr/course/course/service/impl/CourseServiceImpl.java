package com.yhr.course.course.service.impl;

import com.yhr.course.course.dao.CourseRepository;
import com.yhr.course.course.dao.TagRepository;
import com.yhr.course.course.entity.Course;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.exception.ServiceException;
import com.yhr.course.course.service.CourseService;
import com.yhr.course.course.utils.PagerHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019-01-09.
 */

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public PagerHelper<Course> list(String key, Integer pageNo, Integer pageSize) {
        PagerHelper<Course> result = new PagerHelper<>();
        StringBuffer sql = new StringBuffer("select * from s_course where 1=1");
        List<Object> params = new ArrayList<>();
        if (StringUtils.isNotEmpty(key)) {
            sql.append(" and course_name like ?");
            params.add("%" + key + "%");
        }

        StringBuffer totalSql = new StringBuffer("select count(1) from (" + sql.toString() + ") a");
        Integer total = jdbcTemplate.queryForObject(totalSql.toString(), params.toArray(), Integer.class);

        int startIndex = (pageNo - 1) * pageSize;
        sql.append(" limit ?,?");
        params.add(startIndex);
        params.add(pageSize);
        List<Course> tags = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<Course>(Course.class));
        result.setTotal(total);
        result.setItems(tags);
        return result;
    }

    @Override
    public Course create(Course course) {
        course.setCreateTime(new Date());
        return courseRepository.save(course);
    }

    @Override
    public Course update(Integer id, Course course) throws Exception {
        Course tempCourse = courseRepository.getOne(id);
        if (tempCourse == null) {
            throw new ServiceException("不存在【" + id + "】对应的课程");
        }
        tempCourse.setCourseName(course.getCourseName());
        tempCourse.setCourseDesc(course.getCourseDesc());
        tempCourse.setCourseRoom(course.getCourseRoom());
        tempCourse.setCourseImageUrl(course.getCourseImageUrl());
        tempCourse.setCourseTip(course.getCourseTip());
        tempCourse.setTeacherId(course.getTeacherId());
        tempCourse.setMaxStudent(course.getMaxStudent());
        tempCourse.setTagId(course.getTagId());
        return courseRepository.save(tempCourse);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Course course = courseRepository.getOne(id);
        if (course == null) {
            throw new ServiceException("不存在【" + id + "】对应的课程");
        }
        courseRepository.delete(course);
    }

    @Override
    public Course get(Integer id) throws Exception {
        Course course = courseRepository.getOne(id);
        if (course == null) {
            throw new ServiceException("不存在【" + id + "】对应的课程");
        }
        return course;
    }

    @Override
    public String upload(MultipartFile multipartFile) throws Exception {
        if (multipartFile == null) {
            return "";
        }
        String fileName = multipartFile.getOriginalFilename();
        String rootPath = Thread.currentThread().getContextClassLoader().getResource(".").getPath();
        String relativePath = "image";
        InputStream inputStream = multipartFile.getInputStream();
        File destDir = new File(rootPath + relativePath);
        if (!destDir.exists()) {
            FileUtils.forceMkdir(destDir);
        }
        File destFile = new File(rootPath + relativePath + File.separator + fileName);
        OutputStream outputStream = new FileOutputStream(destFile);
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        return fileName;
    }

    @Override
    public void downloadImage(String relativePath, HttpServletResponse response) throws Exception {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource(".").getPath();
        String path = rootPath +"image" +File.separator+ relativePath;
        OutputStream outputStream = response.getOutputStream();
        InputStream iStream = new FileInputStream(new File(path));
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = iStream.read(buffer)) != -1) {
            outputStream.write(buffer,0,len);
        }
    }
}