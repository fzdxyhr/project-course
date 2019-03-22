package com.yhr.course.course.service.impl;

import com.yhr.course.course.config.GaeaContext;
import com.yhr.course.course.dao.AnswerRepository;
import com.yhr.course.course.dao.CourseRepository;
import com.yhr.course.course.dao.QuestionRepository;
import com.yhr.course.course.entity.*;
import com.yhr.course.course.exception.ServiceException;
import com.yhr.course.course.service.QuestionService;
import com.yhr.course.course.service.UserService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.AnswerVo;
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
    @Autowired
    private AnswerRepository answerRepository;

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
                questionVo.setAnswerVos(findAnswerByQuestionId(questionVo.getId(), userMap));
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

    @Override
    public QuestionVo get(Integer id) throws Exception {
        Question question = questionRepository.getOne(id);
        if (question == null) {
            throw new ServiceException("不存在对应的问题");
        }
        QuestionVo questionVo = new QuestionVo();
        BeanUtils.copyProperties(question, questionVo);
        Map<Integer, User> userMap = userService.getAllUserMap();
        questionVo.setUserName(questionVo.getUserId() == null || userMap.get(questionVo.getUserId()) == null ? "" : userMap.get(questionVo.getUserId()).getUserName());
        questionVo.setPhotoPath(questionVo.getUserId() == null || userMap.get(questionVo.getUserId()) == null ? "" : userMap.get(questionVo.getUserId()).getPhotoPath());
        //查询对应的回复信息
        List<AnswerVo> answerVos = findAnswerByQuestionId(id, userMap);
        questionVo.setAnswerVos(answerVos);
        return questionVo;
    }

    private List<AnswerVo> findAnswerByQuestionId(Integer questionId, Map<Integer, User> userMap) {
        //查询对应的回复信息
        List<Answer> answers = answerRepository.findByQuestionId(questionId);
        if (CollectionUtils.isEmpty(answers)) {
            return new ArrayList<>();
        }
        List<AnswerVo> answerVos = new ArrayList<>();
        for (Answer answer : answers) {
            AnswerVo answerVo = new AnswerVo();
            BeanUtils.copyProperties(answer, answerVo);
            answerVo.setUserName(answer.getUserId() == null || userMap.get(answer.getUserId()) == null ? "" : userMap.get(answer.getUserId()).getUserName());
            answerVo.setUserPhotoPath(answer.getUserId() == null || userMap.get(answer.getUserId()) == null ? "" : userMap.get(answer.getUserId()).getPhotoPath());
            answerVos.add(answerVo);
        }
        return answerVos;
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
