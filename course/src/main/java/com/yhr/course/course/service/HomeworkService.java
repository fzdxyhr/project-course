package com.yhr.course.course.service;

import com.yhr.course.course.entity.Homework;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.utils.PagerHelper;

/**
 * Created by Administrator on 2019-01-10.
 */
public interface HomeworkService {

    PagerHelper<Homework> list(String key, Integer pageNo, Integer pageSize);

    Homework create(Homework homework);

    Homework update(Integer id, Homework homework) throws Exception;

    void delete(Integer id) throws Exception;

    Homework get(Integer id) throws Exception;

}