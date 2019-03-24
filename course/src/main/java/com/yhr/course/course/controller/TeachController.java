package com.yhr.course.course.controller;

import com.yhr.course.course.entity.Teach;
import com.yhr.course.course.service.TeachService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.ChapterTreeVo;
import com.yhr.course.course.vo.TeachVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2019-01-10.
 */

@RestController
@RequestMapping("/v1")
public class TeachController {

    @Autowired
    private TeachService teachService;

    @RequestMapping(value = "/teachs", method = RequestMethod.GET)
    public PagerHelper<TeachVo> list(@RequestParam(value = "key", required = false) String key
            , @RequestParam(value = "page_no", defaultValue = "1") Integer pageNo
            , @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) {
        return teachService.list(key, pageNo, pageSize);
    }

    @RequestMapping(value = "/teachs", method = RequestMethod.POST)
    public TeachVo create(@RequestBody TeachVo tag) {
        return teachService.create(tag);
    }

    @RequestMapping(value = "/teachs/{id}", method = RequestMethod.PUT)
    public TeachVo update(@PathVariable("id") Integer id, @RequestBody TeachVo tag) throws Exception {
        return teachService.update(id, tag);
    }

    @RequestMapping(value = "/teachs/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) throws Exception {
        teachService.delete(id);
    }

    @RequestMapping(value = "/teachs/{id}", method = RequestMethod.GET)
    public Teach get(@PathVariable("id") Integer id) throws Exception {
        return teachService.get(id);
    }
    @RequestMapping(value = "/courses/chapter/tree", method = RequestMethod.GET)
    public List<ChapterTreeVo> chapterTree() throws Exception {
        return teachService.chapterTree();
    }

}