package com.yhr.course.course.service.impl;

import com.yhr.course.course.dao.ClassesRepository;
import com.yhr.course.course.dao.TagRepository;
import com.yhr.course.course.dao.UserRepository;
import com.yhr.course.course.entity.Classes;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.entity.User;
import com.yhr.course.course.exception.ServiceException;
import com.yhr.course.course.service.ClassesService;
import com.yhr.course.course.service.UserService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.StudentVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author yhr
 * @version latest
 * @date 2019/2/27
 */

@Service
public class ClassesServiceImpl implements ClassesService {


    @Autowired
    private ClassesRepository classesRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public PagerHelper<Classes> list(String key, Integer pageNo, Integer pageSize) {
        PagerHelper<Classes> result = new PagerHelper<>();
        StringBuffer sql = new StringBuffer("select * from s_classes where 1=1");
        List<Object> params = new ArrayList<>();
        if (StringUtils.isNotEmpty(key)) {
            sql.append(" and class_name like ?");
            params.add("%" + key + "%");
        }

        StringBuffer totalSql = new StringBuffer("select count(1) from (" + sql.toString() + ") a");
        Integer total = jdbcTemplate.queryForObject(totalSql.toString(), params.toArray(), Integer.class);

        int startIndex = (pageNo - 1) * pageSize;
        sql.append(" limit ?,?");
        params.add(startIndex);
        params.add(pageSize);
        List<Classes> classesList = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<Classes>(Classes.class));
        if (CollectionUtils.isNotEmpty(classesList)) {
            Map<Integer, User> userMap = userService.getAllUserMap();
            for (Classes classes : classesList) {
                classes.setTeacherName(classes.getTeacherId() == null || userMap.get(classes.getTeacherId()) == null ? "" : userMap.get(classes.getTeacherId()).getUserName());
                classes.setMonitorName(classes.getMonitor() == null || userMap.get(classes.getMonitor()) == null ? "" : userMap.get(classes.getMonitor()).getUserName());
            }
        }
        result.setTotal(total);
        result.setItems(classesList);
        return result;
    }

    @Override
    public Classes create(Classes classes) {
        classes.setCreateTime(new Date());
        return classesRepository.save(classes);
    }

    @Override
    public Classes update(Integer id, Classes classes) throws Exception {
        Classes tempTag = classesRepository.getOne(id);
        if (tempTag == null) {
            throw new ServiceException("不存在【" + id + "】对应的班级");
        }
        BeanUtils.copyProperties(classes, tempTag);
        return classesRepository.save(tempTag);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Classes tempClasses = classesRepository.getOne(id);
        if (tempClasses == null) {
            throw new ServiceException("不存在【" + id + "】对应的班级");
        }
        classesRepository.delete(tempClasses);
    }

    @Override
    public Classes get(Integer id) throws Exception {
        Classes tempClasses = classesRepository.getOne(id);
        if (tempClasses == null) {
            throw new ServiceException("不存在【" + id + "】对应的班级");
        }
        return tempClasses;
    }

    @Override
    public List<StudentVo> findClassesStudent(Integer classId) {
        List<User> users = userRepository.findByClassId(classId);
        if (CollectionUtils.isEmpty(users)) {
            return Collections.emptyList();
        }
        List<StudentVo> studentVos = new ArrayList<>();
        for (User user : users) {
            StudentVo studentVo = new StudentVo();

        }
        return null;
    }
}
