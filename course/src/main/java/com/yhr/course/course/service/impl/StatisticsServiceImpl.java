package com.yhr.course.course.service.impl;

import com.yhr.course.course.dao.UserRepository;
import com.yhr.course.course.entity.User;
import com.yhr.course.course.service.StatisticsService;
import com.yhr.course.course.utils.DateUtils;
import com.yhr.course.course.vo.CourseStatisticVo;
import com.yhr.course.course.vo.HomeworkStatisticVo;
import com.yhr.course.course.vo.SignStatisticVo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2019/3/24.
 */

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<SignStatisticVo> getSignStatistic(Integer days, Integer classId) {
        List<SignStatisticVo> result = new ArrayList<>();
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
        Map<String, Integer> signMap = new HashMap<>();
        for (SignStatisticVo signStatisticVo : signStatisticVos) {
            signMap.put(signStatisticVo.getDate(), signStatisticVo.getSign());
        }
        //获取近几天数据
        List<String> dates = DateUtils.getDayBetween(days);
        //获取班级人数
        List<User> users = userRepository.findByClassId(classId);
        for (String date : dates) {
            SignStatisticVo signStatisticVo = new SignStatisticVo();
            signStatisticVo.setSign(signMap.get(date) == null ? 0 : signMap.get(date));
            signStatisticVo.setNotSign(users.size() - signStatisticVo.getSign());
            signStatisticVo.setDate(date);
            result.add(signStatisticVo);
        }
        return result;
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

    @Override
    public List<CourseStatisticVo> getCourseStatistic(Integer classId) {
        //获取班级下所有用户信息
        List<User> users = userRepository.findByClassId(classId);
        if (CollectionUtils.isEmpty(users)) {
            return Collections.EMPTY_LIST;
        }
        List<CourseStatisticVo> courseStatisticVos = new ArrayList<>();
        Map<String, Integer> studyCourseMap = getUserStudy();
        //设置用户学习课程数
        for (User user : users) {
            CourseStatisticVo courseStatisticVo = new CourseStatisticVo();
            courseStatisticVo.setUserName(user.getUserName());
            courseStatisticVo.setStudyCourse(studyCourseMap.getOrDefault(user.getUserName(), 0));
            courseStatisticVos.add(courseStatisticVo);
        }
        return courseStatisticVos;
    }

    private Map<String, Integer> getUserStudy() {
        List<Object> params = new ArrayList<>();
        StringBuffer sql = new StringBuffer("SELECT us.user_name,COUNT(cs.course_id) study_course from s_user_study_progress cs");
        sql.append(" LEFT JOIN s_user us on us.id=cs.user_id GROUP BY cs.user_id");
        List<CourseStatisticVo> homeworkStatisticVos = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(CourseStatisticVo.class));
        Map<String, Integer> studyCourseMap = new HashMap<>();
        if (CollectionUtils.isEmpty(homeworkStatisticVos)) {
            return studyCourseMap;
        }
        for (CourseStatisticVo homeworkStatisticVo : homeworkStatisticVos) {
            studyCourseMap.put(homeworkStatisticVo.getUserName(), homeworkStatisticVo.getStudyCourse());
        }
        return studyCourseMap;
    }
}
