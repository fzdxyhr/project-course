package com.yhr.course.course.controller;

import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.service.TagService;
import com.yhr.course.course.utils.PagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2019-01-10.
 */

@RestController
@RequestMapping("/v1")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public PagerHelper<Tag> list(@RequestParam(value = "key", required = false) String key
            , @RequestParam(value = "page_no", defaultValue = "1") Integer pageNo
            , @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) {
        return tagService.list(key, pageNo, pageSize);
    }

    @RequestMapping(value = "/tags", method = RequestMethod.POST)
    public Tag create(@RequestBody Tag tag) {
        return tagService.create(tag);
    }

    @RequestMapping(value = "/tags/{id}", method = RequestMethod.PUT)
    public Tag update(@PathVariable("id") Integer id, @RequestBody Tag tag) throws Exception {
        return tagService.update(id, tag);
    }

    @RequestMapping(value = "/tags/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) throws Exception {
        tagService.delete(id);
    }

    @RequestMapping(value = "/tags/{id}", method = RequestMethod.GET)
    public Tag get(@PathVariable("id") Integer id) throws Exception {
        return tagService.get(id);
    }

}