package com.yhr.course.course.service.impl;

import com.yhr.course.course.dao.TagRepository;
import com.yhr.course.course.dao.UserRepository;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.entity.User;
import com.yhr.course.course.exception.ServiceException;
import com.yhr.course.course.service.TagService;
import com.yhr.course.course.service.UserService;
import com.yhr.course.course.utils.PagerHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019-01-10.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public PagerHelper<User> list(String key, String role, Integer pageNo, Integer pageSize) {
        PagerHelper<User> result = new PagerHelper<>();
        StringBuffer sql = new StringBuffer("select * from s_user where 1=1");
        List<Object> params = new ArrayList<>();
        if (StringUtils.isNotEmpty(key)) {
            sql.append(" and user_name like ?");
            params.add("%" + key + "%");
        }
        if (StringUtils.isNotEmpty(role)) {
            sql.append(" and role = ?");
            params.add(role);
        }
        StringBuffer totalSql = new StringBuffer("select count(1) from (" + sql.toString() + ") a");
        Integer total = jdbcTemplate.queryForObject(totalSql.toString(), params.toArray(), Integer.class);

        int startIndex = (pageNo - 1) * pageSize;
        sql.append(" limit ?,?");
        params.add(startIndex);
        params.add(pageSize);
        List<User> users = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<User>(User.class));
        result.setTotal(total);
        result.setItems(users);
        return result;
    }

    @Override
    public User create(User user) {
        user.setCreateTime(new Date());
        return userRepository.save(user);
    }

    @Override
    public User update(Integer id, User user) throws Exception {
        User tempUser = userRepository.getOne(id);
        if (tempUser == null) {
            throw new ServiceException("不存在【" + id + "】对应的标签");
        }
        BeanUtils.copyProperties(user, tempUser);
        return userRepository.save(tempUser);
    }

    @Override
    public void delete(Integer id) throws Exception {
        User tempTag = userRepository.getOne(id);
        if (tempTag == null) {
            throw new ServiceException("不存在【" + id + "】对应的标签");
        }
        userRepository.delete(tempTag);
    }

    @Override
    public User get(Integer id) throws Exception {
        User tempUser = userRepository.getOne(id);
        if (tempUser == null) {
            throw new ServiceException("不存在【" + id + "】对应的标签");
        }
        return tempUser;
    }
}
