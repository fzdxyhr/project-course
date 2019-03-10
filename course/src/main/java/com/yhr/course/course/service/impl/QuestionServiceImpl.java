package com.yhr.course.course.service.impl;

import com.yhr.course.course.config.GaeaContext;
import com.yhr.course.course.dao.CourseRepository;
import com.yhr.course.course.dao.QuestionRepository;
import com.yhr.course.course.entity.Course;
import com.yhr.course.course.entity.CourseComment;
import com.yhr.course.course.entity.Question;
import com.yhr.course.course.entity.User;
import com.yhr.course.course.service.QuestionService;
import com.yhr.course.course.service.UserService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.CourseCommentVo;
import com.yhr.course.course.vo.QuestionVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2019/3/8.
 */

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public PagerHelper<QuestionVo> list(String key, Integer userId, Integer courseId, Integer pageNo, Integer pageSize) {
        PagerHelper<QuestionVo> result = new PagerHelper<>();
        StringBuffer sql = new StringBuffer("select * from s_question where 1=1");
        List<Object> params = new ArrayList<>();
        if (StringUtils.isNotEmpty(key)) {
            sql.append(" and question_content like ?");
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
        List<QuestionVo> questionVos = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(QuestionVo.class));
        if (CollectionUtils.isNotEmpty(questionVos)) {
            Map<Integer, User> userMap = userService.getAllUserMap();
            Map<Integer, Course> courseMap = getCourseMap();
            for (QuestionVo questionVo : questionVos) {
                questionVo.setUserName(questionVo.getUserId() == null || userMap.get(questionVo.getUserId()) == null ? "" : userMap.get(questionVo.getUserId()).getUserName());
                questionVo.setPhotoPath(questionVo.getUserId() == null || userMap.get(questionVo.getUserId()) == null ? "" : userMap.get(questionVo.getUserId()).getPhotoPath());
                questionVo.setCourseName(questionVo.getCourseId() == null || courseMap.get(questionVo.getCourseId()) == null ? "" : courseMap.get(questionVo.getCourseId()).getCourseName());
                questionVo.setCoursePath(questionVo.getCourseId() == null || courseMap.get(questionVo.getCourseId()) == null ? "" : courseMap.get(questionVo.getCourseId()).getCourseImageUrl());
            }
        }
        result.setTotal(total);
        result.setItems(questionVos);
        return result;
    }

    @Override
    public QuestionVo create(QuestionVo questionVo) {
        Question question = new Question();
        BeanUtils.copyProperties(questionVo, question);
        question.setUserId(GaeaContext.getUserId());
        question.setCreateTime(new Date());
        questionRepository.save(question);
        return questionVo;
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
