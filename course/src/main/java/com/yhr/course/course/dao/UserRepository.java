package com.yhr.course.course.dao;

import com.yhr.course.course.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2019-01-09.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByAccountAndPasswordAndStatus(String account, String password, Integer status);

    List<User> findByClassId(Integer classId);

    User findByAccount(String account);

}
