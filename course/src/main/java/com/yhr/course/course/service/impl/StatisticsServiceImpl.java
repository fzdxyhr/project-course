package com.yhr.course.course.service.impl;

import com.yhr.course.course.service.StatisticsService;
import com.yhr.course.course.vo.HomeworkStatisticVo;
import com.yhr.course.course.vo.SignStatisticVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/24.
 */

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<SignStatisticVo> getSignStatistic(Integer classId) {
        List<Object> params = new ArrayList<>();
        StringBuffer sql = new StringBuffer("SELECT DATE_FORMAT(si.create_time,\"%Y-%m-%d\") date,count(si.student_id) sign from s_sign si ");
        sql.append(" LEFT JOIN s_user us on us.id = si.student_id");
        sql.append(" where 1=1");
        if (classId != null) {
            sql.append(" and us.class_id = ?");
            params.add(classId);
        }
        sql.append(" GROUP BY DATE_FORMAT(si.create_time,\"%Y-%m-%d\")");
        List<SignStatisticVo> signStatisticVos = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<SignStatisticVo>(SignStatisticVo.class));
        return signStatisticVos;
    }

    @Override
    public List<HomeworkStatisticVo> getHomeworkStatistic(Integer teacherId) {
        List<Object> params = new ArrayList<>();
        StringBuffer sql = new StringBuffer("SELECT home.homework_title title,COUNT(sub.user_id) user from  s_homework_submit sub ");
        sql.append(" LEFT JOIN s_homework home on sub.homework_id = home.id");
        sql.append(" where 1=1");
        if (teacherId != null) {
            sql.append(" and home.publish_teacher = ?");
            params.add(teacherId);
        }
        sql.append(" GROUP BY sub.homework_id");
        List<HomeworkStatisticVo> homeworkStatisticVos = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(HomeworkStatisticVo.class));
        return homeworkStatisticVos;
    }
}
