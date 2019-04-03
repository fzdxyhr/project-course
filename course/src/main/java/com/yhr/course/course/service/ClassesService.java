package com.yhr.course.course.service;

import com.yhr.course.course.entity.Classes;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.StudentVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019-01-10.
 */
public interface ClassesService {

    PagerHelper<Classes> list(String key, Integer pageNo, Integer pageSize) throws Exception;

    Classes create(Classes classes);

    Classes update(Integer id, Classes classes) throws Exception;

    void delete(Integer id) throws Exception;

    Classes get(Integer id) throws Exception;

    List<StudentVo> findClassesStudent(Integer classId);

    void exportStudent(HttpServletResponse response,Integer classId);

    void exportSignStudent(HttpServletResponse response,Integer classId);

    Map<Integer, Classes> getAllClassesMap();

}
