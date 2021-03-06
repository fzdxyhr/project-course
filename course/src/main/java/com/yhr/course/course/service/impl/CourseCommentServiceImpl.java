package com.yhr.course.course.service.impl;

import com.yhr.course.course.config.GaeaContext;
import com.yhr.course.course.dao.CourseCommentRepository;
import com.yhr.course.course.dao.CourseRepository;
import com.yhr.course.course.entity.Course;
import com.yhr.course.course.entity.CourseChapter;
import com.yhr.course.course.entity.CourseComment;
import com.yhr.course.course.entity.User;
import com.yhr.course.course.service.CourseCommentService;
import com.yhr.course.course.service.UserService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.CourseCommentVo;
import com.yhr.course.course.vo.CourseVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2019/3/7.
 */

@Service
public class CourseCommentServiceImpl implements CourseCommentService {

    @Autowired
    private CourseCommentRepository courseCommentRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public PagerHelper<CourseCommentVo> list(String key, Integer userId, Integer courseId, Integer pageNo, Integer pageSize) {
        PagerHelper<CourseCommentVo> result = new PagerHelper<>();
        StringBuffer sql = new StringBuffer("select * from s_course_comment where 1=1");
        List<Object> params = new ArrayList<>();
        if (StringUtils.isNotEmpty(key)) {
            sql.append(" and course_name like ?");
            params.add("%" + key + "%");
        }
        if (courseId != null) {
            sql.append(" and course_id = ?");
            params.add(courseId);
        }
        if (userId != null) {
            sql.append(" and user_id = ?");
            params.add(userId);
        }
        StringBuffer totalSql = new StringBuffer("select count(1) from (" + sql.toString() + ") a");
        Integer total = jdbcTemplate.queryForObject(totalSql.toString(), params.toArray(), Integer.class);

        int startIndex = (pageNo - 1) * pageSize;
        sql.append(" limit ?,?");
        params.add(startIndex);
        params.add(pageSize);
        List<CourseCommentVo> courseVos = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(CourseCommentVo.class));
        if (CollectionUtils.isNotEmpty(courseVos)) {
            Map<Integer, User> userMap = userService.getAllUserMap();
            Map<Integer, Course> courseMap = getCourseMap();
            for (CourseCommentVo courseVo : courseVos) {
                courseVo.setUserName(courseVo.getUserId() == null || userMap.get(courseVo.getUserId()) == null ? "" : userMap.get(courseVo.getUserId()).getUserName());
                courseVo.setPhotoPath(courseVo.getUserId() == null || userMap.get(courseVo.getUserId()) == null ? "" : userMap.get(courseVo.getUserId()).getPhotoPath());
                courseVo.setCourseName(courseVo.getCourseId() == null || courseMap.get(courseVo.getCourseId()) == null ? "" : courseMap.get(courseVo.getCourseId()).getCourseName());
                courseVo.setCoursePath(courseVo.getCourseId() == null || courseMap.get(courseVo.getCourseId()) == null ? "" : courseMap.get(courseVo.getCourseId()).getCourseImageUrl());
            }
        }
        result.setTotal(total);
        result.setItems(courseVos);
        return result;
    }

    @Override
    public CourseCommentVo create(CourseCommentVo courseCommentVo) {
        CourseComment courseComment = new CourseComment();
        BeanUtils.copyProperties(courseCommentVo, courseComment);
        courseComment.setUserId(GaeaContext.getUserId());
        courseComment.setCreateTime(new Date());
        courseCommentRepository.save(courseComment);
        return courseCommentVo;
    }


    private Map<Integer, Course> getCourseMap() {
        Map<Integer, Course> courseMap = new HashMap<>();
        List<Course> courses = courseRepository.findAll();
        if (CollectionUtils.isEmpty(courses)) {
            return courseMap;
        }
        for (Course course : courses) {
            courseMap.put(course.getId(), course);
        }
        return courseMap;
    }
}
