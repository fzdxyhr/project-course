package com.yhr.course.course.controller;

import com.yhr.course.course.entity.User;
import com.yhr.course.course.service.UserService;
import com.yhr.course.course.utils.PagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2019-01-10.
 */

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public PagerHelper<User> list(@RequestParam(value = "key", required = false) String key
            , @RequestParam(value = "role", required = false) String role
            , @RequestParam(value = "page_no", defaultValue = "1") Integer pageNo
            , @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) {
        return userService.list(key, role, pageNo, pageSize);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public User update(@PathVariable("id") Integer id, @RequestBody User user) throws Exception {
        return userService.update(id, user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) throws Exception {
        userService.delete(id);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") Integer id) throws Exception {
        return userService.get(id);
    }

}