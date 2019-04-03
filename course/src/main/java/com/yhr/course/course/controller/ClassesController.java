package com.yhr.course.course.controller;

import com.yhr.course.course.entity.Classes;
import com.yhr.course.course.entity.Classes;
import com.yhr.course.course.service.ClassesService;
import com.yhr.course.course.service.ClassesService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2019-01-10.
 */

@RestController
@RequestMapping("/v1")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @RequestMapping(value = "/classes", method = RequestMethod.GET)
    public PagerHelper<Classes> list(@RequestParam(value = "key", required = false) String key
            , @RequestParam(value = "page_no", defaultValue = "1") Integer pageNo
            , @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) throws Exception {
        return classesService.list(key, pageNo, pageSize);
    }

    @RequestMapping(value = "/classes", method = RequestMethod.POST)
    public Classes create(@RequestBody Classes tag) {
        return classesService.create(tag);
    }

    @RequestMapping(value = "/classes/{id}", method = RequestMethod.PUT)
    public Classes update(@PathVariable("id") Integer id, @RequestBody Classes tag) throws Exception {
        return classesService.update(id, tag);
    }

    @RequestMapping(value = "/classes/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) throws Exception {
        classesService.delete(id);
    }

    @RequestMapping(value = "/classes/{id}", method = RequestMethod.GET)
    public Classes get(@PathVariable("id") Integer id) throws Exception {
        return classesService.get(id);
    }

    @RequestMapping(value = "/classes/{id}/students", method = RequestMethod.GET)
    public List<StudentVo> getStudents(@PathVariable("id") Integer id) throws Exception {
        return classesService.findClassesStudent(id);
    }

    @RequestMapping(value = "/classes/{id}/students/export", method = RequestMethod.GET)
    public void exportStudent(HttpServletResponse response, @PathVariable("id") Integer id) throws Exception {
        classesService.exportStudent(response, id);
    }

    @RequestMapping(value = "/classes/{id}/students/sign/export", method = RequestMethod.GET)
    public void exportSignStudent(HttpServletResponse response, @PathVariable("id") Integer id) throws Exception {
        classesService.exportSignStudent(response, id);
    }

}