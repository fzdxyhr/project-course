package com.yhr.course.course.controller;

import com.yhr.course.course.entity.Homework;
import com.yhr.course.course.entity.HomeworkSubmit;
import com.yhr.course.course.service.HomeworkService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.HomeworkUserVo;
import com.yhr.course.course.vo.HomeworkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/homeworks/front", method = RequestMethod.GET)
    public PagerHelper<HomeworkVo> listFront(@RequestParam(value = "key", required = false) String key
            , @RequestParam(value = "page_no", defaultValue = "1") Integer pageNo
            , @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) throws Exception {
        return homeworkService.listFront(key, pageNo, pageSize);
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

    @RequestMapping(value = "/homeworks/submit", method = RequestMethod.POST)
    public HomeworkSubmit submit(@RequestBody HomeworkSubmit homeworkSubmit) {
        return homeworkService.submit(homeworkSubmit);
    }

    @RequestMapping(value = "/homeworks/{id}/users", method = RequestMethod.GET)
    public List<HomeworkUserVo> listUsers(@PathVariable("id") Integer id) {
        return homeworkService.listUsers(id);
    }

    @RequestMapping(value = "/homeworks/{id}/users/{user_id}/score", method = RequestMethod.PUT)
    public void score(@PathVariable("id") Integer id, @PathVariable("user_id") Integer userId, @RequestParam("score") Integer score) {
        homeworkService.score(id, userId, score);
    }

}