package com.yhr.course.course.service;

import com.yhr.course.course.entity.Classes;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.utils.PagerHelper;

/**
 * Created by Administrator on 2019-01-10.
 */
public interface ClassesService {

    PagerHelper<Classes> list(String key, Integer pageNo, Integer pageSize);

    Classes create(Classes classes);

    Classes update(Integer id, Classes classes) throws Exception;

    void delete(Integer id) throws Exception;

    Classes get(Integer id) throws Exception;

}
