package com.yhr.course.course.service.impl;

import com.yhr.course.course.dao.CourseCommentRepository;
import com.yhr.course.course.dao.CourseRepository;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Override
    public PagerHelper<CourseCommentVo> list(String key, Integer pageNo, Integer pageSize) {
        PagerHelper<CourseCommentVo> result = new PagerHelper<>();
        StringBuffer sql = new StringBuffer("select * from s_course_comment where 1=1");
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
        List<CourseCommentVo> courseVos = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(CourseCommentVo.class));
        if (CollectionUtils.isNotEmpty(courseVos)) {
            Map<Integer, User> userMap = userService.getAllUserMap();
            for (CourseCommentVo courseVo : courseVos) {
                courseVo.setUserName(courseVo.getUserId() == null || userMap.get(courseVo.getUserId()) == null ? "" : userMap.get(courseVo.getUserId()).getUserName());
                courseVo.setPhotoPath(courseVo.getUserId() == null || userMap.get(courseVo.getUserId()) == null ? "" : userMap.get(courseVo.getUserId()).getPhotoPath());
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
        courseComment.setCreateTime(new Date());
        courseCommentRepository.save(courseComment);
        return courseCommentVo;
    }
}
