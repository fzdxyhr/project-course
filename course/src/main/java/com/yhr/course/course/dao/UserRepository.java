package com.yhr.course.course.dao;

import com.yhr.course.course.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2019-01-09.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByAccountAndPasswordAndIsAdminAndStatus(String account, String password,Integer isAdmin,Integer status);

    List<User> findByClassId(Integer classId);
}
