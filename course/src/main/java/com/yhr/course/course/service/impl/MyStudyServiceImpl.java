package com.yhr.course.course.service.impl;

import com.yhr.course.course.config.GaeaContext;
import com.yhr.course.course.dao.CourseRepository;
import com.yhr.course.course.dao.CourseStudentRepository;
import com.yhr.course.course.entity.CourseChapter;
import com.yhr.course.course.entity.CourseStudent;
import com.yhr.course.course.service.CourseCommentService;
import com.yhr.course.course.service.MyStudyService;
import com.yhr.course.course.service.QuestionService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.CourseCommentVo;
import com.yhr.course.course.vo.CourseVo;
import com.yhr.course.course.vo.QuestionVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/8.
 */

@Service
public class MyStudyServiceImpl implements MyStudyService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CourseCommentService courseCommentService;

    @Override
    public PagerHelper<CourseVo> findMyCourse(Integer pageNo, Integer pageSize) {
        PagerHelper<CourseVo> result = new PagerHelper<>();
        StringBuffer sql = new StringBuffer("select a.* from s_course a,s_course_student b where 1=1 and a.id = b.course_id");
        List<Object> params = new ArrayList<>();
        sql.append(" and b.student_id = ?");
        params.add(GaeaContext.getUserId());

        StringBuffer totalSql = new StringBuffer("select count(1) from (" + sql.toString() + ") a");
        Integer total = jdbcTemplate.queryForObject(totalSql.toString(), params.toArray(), Integer.class);

        int startIndex = (pageNo - 1) * pageSize;
        sql.append(" limit ?,?");
        params.add(startIndex);
        params.add(pageSize);
        List<CourseVo> courseVos = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(CourseVo.class));
        result.setTotal(total);
        result.setItems(courseVos);
        return result;
    }

    @Override
    public PagerHelper<CourseCommentVo> listComments(Integer pageNo, Integer pageSize) {
        return courseCommentService.list(null, GaeaContext.getUserId(), null, pageNo, pageSize);
    }

    @Override
    public PagerHelper<QuestionVo> listQuestions(Integer pageNo, Integer pageSize) {
        return questionService.list(null, GaeaContext.getUserId(), null, pageNo, pageSize);
    }
}
