package com.yhr.course.course.controller;

import com.yhr.course.course.entity.Course;
import com.yhr.course.course.entity.CourseStudent;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.service.CourseService;
import com.yhr.course.course.service.TagService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.CourseVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2019-01-10.
 */

@RestController
@RequestMapping("/v1")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public PagerHelper<CourseVo> list(@RequestParam(value = "key", required = false) String key
            , @RequestParam(value = "tag_id", required = false) Integer tagId
            , @RequestParam(value = "page_no", defaultValue = "1") Integer pageNo
            , @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) {
        return courseService.list(key, tagId, pageNo, pageSize);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    public Course create(@RequestBody Course course) {
        return courseService.create(course);
    }

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.PUT)
    public Course update(@PathVariable("id") Integer id, @RequestBody CourseVo course) throws Exception {
        return courseService.update(id, course);
    }

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) throws Exception {
        courseService.delete(id);
    }

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
    public CourseVo get(@PathVariable("id") Integer id) throws Exception {
        return courseService.get(id);
    }

    @RequestMapping(value = "/courses/recommend", method = RequestMethod.GET)
    public List<CourseVo> recommend() throws Exception {
        return courseService.recommend();
    }

    @RequestMapping(value = "/courses/{id}/study", method = RequestMethod.PUT)
    public void study(@PathVariable("id") Integer id, @RequestParam("chapter_id") Integer chapterId) throws Exception {
        courseService.study(id, chapterId);
    }

    @RequestMapping(value = "/courses/{id}/study", method = RequestMethod.GET)
    public boolean getStudy(@PathVariable("id") Integer id) throws Exception {
        return courseService.getStudy(id);
    }

    @ApiOperation(value = "上传图片", notes = "")
    @RequestMapping(value = "/courses/images/upload", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
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

    @ApiOperation(value = "上传文件", notes = "")

    @RequestMapping(value = "/files/upload", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String uploadFile(@ApiParam(value = "附件流", required = true) MultipartHttpServletRequest multiReq) throws Exception {
        MultipartFile multipartFile = null;
        for (String key : multiReq.getMultiFileMap().keySet()) {
            multipartFile = multiReq.getFile(key);
            break;
        }
        String result = courseService.uploadFile(multipartFile);
        return result;
    }

    @ApiOperation(value = "下载文件", notes = "")
    @RequestMapping(value = "/files/download/{file_name}", method = RequestMethod.GET)
    public void downloadFile(@ApiParam(value = "文件名", required = true) @PathVariable("file_name") String fileName, HttpServletResponse response) throws Exception {
        courseService.downloadFile(fileName, response);
    }

}