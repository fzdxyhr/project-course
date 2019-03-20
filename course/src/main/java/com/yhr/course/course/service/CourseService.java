package com.yhr.course.course.service;

import com.yhr.course.course.entity.Course;
import com.yhr.course.course.entity.CourseStudent;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.CourseVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2019-01-09.
 */
public interface CourseService {

    PagerHelper<CourseVo> list(String key, Integer tagId, Integer pageNo, Integer pageSize);

    Course create(Course course);

    Course update(Integer id, CourseVo course) throws Exception;

    void delete(Integer id) throws Exception;

    CourseVo get(Integer id) throws Exception;

    void study(Integer id, Integer chapterId) throws Exception;

    boolean getStudy(Integer id) throws Exception;

    String upload(MultipartFile multipartFile) throws Exception;

    String uploadFile(MultipartFile multipartFile) throws Exception;

    void downloadImage(String fileName, HttpServletResponse response) throws Exception;

    void downloadFile(String fileName, HttpServletResponse response) throws Exception;

    List<CourseVo> recommend();
}
