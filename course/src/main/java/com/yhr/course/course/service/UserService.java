package com.yhr.course.course.service;

import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.entity.User;
import com.yhr.course.course.utils.PagerHelper;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2019-01-10.
 */
public interface UserService {

    PagerHelper<User> list(String key,String role, Integer pageNo, Integer pageSize);

    User create(User user);

    User update(Integer id, User user) throws Exception;

    void delete(Integer id) throws Exception;

    User get(Integer id) throws Exception;

    void downloadTemplate(HttpServletResponse response) throws Exception;

    void parseExcel(MultipartFile multipartFile) throws Exception;

}
