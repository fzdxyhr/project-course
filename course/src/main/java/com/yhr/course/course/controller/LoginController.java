package com.yhr.course.course.controller;

import com.yhr.course.course.dao.UserRepository;
import com.yhr.course.course.entity.User;
import com.yhr.course.course.exception.ServiceException;
import com.yhr.course.course.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/2/17.
 */

@RestController
@RequestMapping("/v1")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestBody UserVo userVo) throws Exception {
        if (userVo == null) {
            return new User();
        }
        User user = userRepository.findByAccountAndPasswordAndIsAdminAndStatus(userVo.getAccount(), userVo.getPassword(), 1, 1);
        if (user == null) {
            throw new ServiceException("不存在对应的【" + userVo.getAccount() + "】账户");
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
        userRepository.save(user);
        return true;
    }
}
