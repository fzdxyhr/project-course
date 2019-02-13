package com.yhr.course.course.controller;

import com.yhr.course.course.entity.Course;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.service.CourseService;
import com.yhr.course.course.service.TagService;
import com.yhr.course.course.utils.PagerHelper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2019-01-10.
 */

@RestController
@RequestMapping("/v1")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public PagerHelper<Course> list(@RequestParam(value = "key", required = false) String key
            , @RequestParam(value = "page_no", defaultValue = "1") Integer pageNo
            , @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) {
        return courseService.list(key, pageNo, pageSize);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    public Course create(@RequestBody Course course) {
        return courseService.create(course);
    }

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.PUT)
    public Course update(@PathVariable("id") Integer id, @RequestBody Course course) throws Exception {
        return courseService.update(id, course);
    }

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) throws Exception {
        courseService.delete(id);
    }

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
    public Course get(@PathVariable("id") Integer id) throws Exception {
        return courseService.get(id);
    }

    @ApiOperation(value = "上传图片", notes = "")
    @RequestMapping(value = "/courses/images/upload", method = RequestMethod.POST)
    public String upload(@ApiParam(value = "附件流", required = true) MultipartHttpServletRequest multiReq) throws Exception {
        MultipartFile multipartFile = null;
        for (String key : multiReq.getMultiFileMap().keySet()) {
            multipartFile = multiReq.getFile(key);
            break;
        }
        String result = courseService.upload(multipartFile);
        return result;
    }

    @ApiOperation(value = "下载图片", notes = "")
    @GetMapping(value = "/courses/images/download")
    public void downloadImage(@ApiParam(value = "文件名", required = true) @RequestParam("relative_path") String relativePath, HttpServletResponse response) throws Exception {
        courseService.downloadImage(relativePath, response);
    }

}