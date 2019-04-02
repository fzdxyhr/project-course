package com.yhr.course.course.controller;

import com.yhr.course.course.contants.RoleEnum;
import com.yhr.course.course.dao.UserRepository;
import com.yhr.course.course.entity.User;
import com.yhr.course.course.exception.ServiceException;
import com.yhr.course.course.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Administrator on 2019/2/17.
 */

@RestController
@RequestMapping("/v1")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public User adminLogin(@RequestBody UserVo userVo) throws Exception {
        if (userVo == null) {
            return new User();
        }
        User user = userRepository.findByAccountAndPasswordAndStatus(userVo.getAccount(), userVo.getPassword(), 1);
        if (user == null) {
            throw new ServiceException("账号或密码错误");
        }
        if (RoleEnum.STUDENT.getValue().equals(user.getRole())) {
            throw new ServiceException("您不是教师或者管理员,无法登录后台,请联系管理员");
        }
        return user;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestBody UserVo userVo) throws Exception {
        if (userVo == null) {
            return new User();
        }
        User user = userRepository.findByAccountAndPasswordAndStatus(userVo.getAccount(), userVo.getPassword(), 1);
        if (user == null) {
            throw new ServiceException("账号或密码错误");
        }

        return user;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public boolean register(@RequestBody UserVo userVo) throws Exception {
        if (userVo == null) {
            return false;
        }
        User user = new User();
        user.setAccount(userVo.getAccount());
        user.setUserName(userVo.getUserName());
        user.setPassword(userVo.getPassword());
        user.setIsAdmin(1);
        user.setStatus(1);
        user.setCreateTime(new Date());
        userRepository.save(user);
        return true;
    }
}
