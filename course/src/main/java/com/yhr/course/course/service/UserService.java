package com.yhr.course.course.service;

import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.entity.User;
import com.yhr.course.course.utils.PagerHelper;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019-01-10.
 */
public interface UserService {

    PagerHelper<User> list(String key, String role, Integer pageNo, Integer pageSize);

    User create(User user) throws Exception;

    User update(Integer id, User user) throws Exception;

    void delete(Integer id) throws Exception;

    void batchDelete(List<Integer> ids) throws Exception;

    User get(Integer id) throws Exception;

    void downloadTemplate(HttpServletResponse response) throws Exception;

    void parseExcel(MultipartFile multipartFile, Integer classId) throws Exception;

    Map<Integer, User> getAllUserMap();

}
