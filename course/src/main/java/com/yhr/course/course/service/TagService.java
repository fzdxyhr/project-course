package com.yhr.course.course.service;

import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.utils.PagerHelper;

import java.util.Map;

/**
 * Created by Administrator on 2019-01-10.
 */
public interface TagService {

    PagerHelper<Tag> list(String key, Integer pageNo, Integer pageSize);

    Tag create(Tag tag);

    Tag update(Integer id, Tag tag) throws Exception;

    void delete(Integer id) throws Exception;

    Tag get(Integer id) throws Exception;

    Map<Integer,String> getAllTagMap();

}
