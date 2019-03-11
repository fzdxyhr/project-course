package com.yhr.course.course.service;

import com.yhr.course.course.entity.Homework;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.HomeworkVo;

/**
 * Created by Administrator on 2019-01-10.
 */
public interface HomeworkService {

    PagerHelper<HomeworkVo> list(String key, Integer pageNo, Integer pageSize);

    HomeworkVo create(HomeworkVo homeworkVo);

    Homework update(Integer id, Homework homework) throws Exception;

    void delete(Integer id) throws Exception;

    Homework get(Integer id) throws Exception;

}
