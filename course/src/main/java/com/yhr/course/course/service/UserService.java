package com.yhr.course.course.service;

import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.entity.User;
import com.yhr.course.course.utils.PagerHelper;

/**
 * Created by Administrator on 2019-01-10.
 */
public interface UserService {

    PagerHelper<User> list(String key,String role, Integer pageNo, Integer pageSize);

    User create(User user);

    User update(Integer id, User user) throws Exception;

    void delete(Integer id) throws Exception;

    User get(Integer id) throws Exception;

}
