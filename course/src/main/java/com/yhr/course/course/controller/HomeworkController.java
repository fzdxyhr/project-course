package com.yhr.course.course.controller;

import com.yhr.course.course.entity.Homework;
import com.yhr.course.course.service.HomeworkService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.HomeworkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2019-01-10.
 */

@RestController
@RequestMapping("/v1")
public class HomeworkController {

    @Autowired
    private HomeworkService homeworkService;

    @RequestMapping(value = "/homeworks", method = RequestMethod.GET)
    public PagerHelper<HomeworkVo> list(@RequestParam(value = "key", required = false) String key
            , @RequestParam(value = "page_no", defaultValue = "1") Integer pageNo
            , @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) {
        return homeworkService.list(key, pageNo, pageSize);
    }

    @RequestMapping(value = "/homeworks", method = RequestMethod.POST)
    public HomeworkVo create(@RequestBody HomeworkVo homeworkVo) {
        return homeworkService.create(homeworkVo);
    }

    @RequestMapping(value = "/homeworks/{id}", method = RequestMethod.PUT)
    public Homework update(@PathVariable("id") Integer id, @RequestBody Homework homework) throws Exception {
        return homeworkService.update(id, homework);
    }

    @RequestMapping(value = "/homeworks/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) throws Exception {
        homeworkService.delete(id);
    }

    @RequestMapping(value = "/homeworks/{id}", method = RequestMethod.GET)
    public Homework get(@PathVariable("id") Integer id) throws Exception {
        return homeworkService.get(id);
    }

}