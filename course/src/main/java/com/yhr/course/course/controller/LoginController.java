package com.yhr.course.course.controller;

import com.yhr.course.course.dao.UserRepository;
import com.yhr.course.course.entity.User;
import com.yhr.course.course.exception.ServiceException;
import com.yhr.course.course.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/2/17.
 */

@RestController
@RequestMapping("/v1")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    public String login(@RequestBody UserVo userVo) throws Exception {
        if (userVo == null) {
            return null;
        }
        User user = userRepository.findByAccountAndPassword(userVo.getAccount(), userVo.getPassword());
        if (user == null) {
            throw new ServiceException("不存在对应的【" + userVo.getAccount() + "】账户");
        }
        return "";
    }
}
