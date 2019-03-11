package com.yhr.course.course.service.impl;

import com.yhr.course.course.config.GaeaContext;
import com.yhr.course.course.dao.HomeworkRepository;
import com.yhr.course.course.entity.Homework;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.entity.User;
import com.yhr.course.course.exception.ServiceException;
import com.yhr.course.course.service.HomeworkService;
import com.yhr.course.course.service.TagService;
import com.yhr.course.course.service.UserService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.HomeworkVo;
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
 * Created by Administrator on 2019-01-10.
 */

@Service
public class HomeworkServiceImpl implements HomeworkService {

    @Autowired
    private HomeworkRepository homeworkRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserService userService;

    @Override
    public PagerHelper<HomeworkVo> list(String key, Integer pageNo, Integer pageSize) {
        PagerHelper<HomeworkVo> result = new PagerHelper<>();
        StringBuffer sql = new StringBuffer("select * from s_homework where 1=1");
        List<Object> params = new ArrayList<>();
        if (StringUtils.isNotEmpty(key)) {
            sql.append(" and homework_title like ?");
            params.add("%" + key + "%");
        }

        StringBuffer totalSql = new StringBuffer("select count(1) from (" + sql.toString() + ") a");
        Integer total = jdbcTemplate.queryForObject(totalSql.toString(), params.toArray(), Integer.class);

        int startIndex = (pageNo - 1) * pageSize;
        sql.append(" limit ?,?");
        params.add(startIndex);
        params.add(pageSize);
        List<HomeworkVo> tags = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<HomeworkVo>(HomeworkVo.class));
        if (CollectionUtils.isNotEmpty(tags)) {
            Map<Integer, User> userMap = userService.getAllUserMap();
            for (HomeworkVo tag : tags) {
                tag.setPublishTeacherName(tag.getPublishTeacher() == null || userMap.get(tag.getPublishTeacher()) == null ? "" : userMap.get(tag.getPublishTeacher()).getUserName());
            }
        }
        result.setTotal(total);
        result.setItems(tags);
        return result;
    }

    @Override
    public HomeworkVo create(HomeworkVo homeworkVo) {
        Homework homework = new Homework();
        BeanUtils.copyProperties(homeworkVo, homework);
        homework.setPublishTeacher(GaeaContext.getAdminUserId());
        homework.setCreateTime(new Date());
        homeworkRepository.save(homework);
        return homeworkVo;
    }

    @Override
    public Homework update(Integer id, Homework homework) throws Exception {
        Homework tempHomework = homeworkRepository.getOne(id);
        if (tempHomework == null) {
            throw new ServiceException("不存在【" + id + "】对应的标签");
        }
        BeanUtils.copyProperties(homework, tempHomework);
        return homeworkRepository.save(tempHomework);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Homework tempTag = homeworkRepository.getOne(id);
        if (tempTag == null) {
            throw new ServiceException("不存在【" + id + "】对应的标签");
        }
        homeworkRepository.delete(tempTag);
    }

    @Override
    public Homework get(Integer id) throws Exception {
        Homework tempTag = homeworkRepository.getOne(id);
        if (tempTag == null) {
            throw new ServiceException("不存在【" + id + "】对应的标签");
        }
        return tempTag;
    }
}
