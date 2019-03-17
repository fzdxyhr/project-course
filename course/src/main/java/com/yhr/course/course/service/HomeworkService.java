package com.yhr.course.course.service;

import com.yhr.course.course.entity.Homework;
import com.yhr.course.course.entity.HomeworkSubmit;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.HomeworkUserVo;
import com.yhr.course.course.vo.HomeworkVo;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by Administrator on 2019-01-10.
 */
public interface HomeworkService {

    PagerHelper<HomeworkVo> list(String key, Integer pageNo, Integer pageSize);

    PagerHelper<HomeworkVo> listFront(String key, Integer pageNo, Integer pageSize) throws Exception;

    HomeworkVo create(HomeworkVo homeworkVo);

    Homework update(Integer id, Homework homework) throws Exception;

    void delete(Integer id) throws Exception;

    Homework get(Integer id) throws Exception;

    HomeworkSubmit submit(HomeworkSubmit homeworkSubmit);

    List<HomeworkUserVo> listUsers(Integer id);

}
