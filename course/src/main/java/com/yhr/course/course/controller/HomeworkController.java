package com.yhr.course.course.controller;

import com.yhr.course.course.entity.Homework;
import com.yhr.course.course.service.HomeworkService;
import com.yhr.course.course.utils.PagerHelper;
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

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public PagerHelper<Homework> list(@RequestParam(value = "key", required = false) String key
            , @RequestParam(value = "page_no", defaultValue = "1") Integer pageNo
            , @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) {
        return homeworkService.list(key, pageNo, pageSize);
    }

    @RequestMapping(value = "/tags", method = RequestMethod.POST)
    public Homework create(@RequestBody Homework homework) {
        return homeworkService.create(homework);
    }

    @RequestMapping(value = "/tags/{id}", method = RequestMethod.PUT)
    public Homework update(@PathVariable("id") Integer id, @RequestBody Homework homework) throws Exception {
        return homeworkService.update(id, homework);
    }

    @RequestMapping(value = "/tags/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) throws Exception {
        homeworkService.delete(id);
    }

    @RequestMapping(value = "/tags/{id}", method = RequestMethod.GET)
    public Homework get(@PathVariable("id") Integer id) throws Exception {
        return homeworkService.get(id);
    }

}