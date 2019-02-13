package com.yhr.course.course.service.impl;

import com.yhr.course.course.dao.TagRepository;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.exception.ServiceException;
import com.yhr.course.course.service.TagService;
import com.yhr.course.course.utils.PagerHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019-01-10.
 */

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public PagerHelper<Tag> list(String key, Integer pageNo, Integer pageSize) {
        PagerHelper<Tag> result = new PagerHelper<>();
        StringBuffer sql = new StringBuffer("select * from s_tag where 1=1");
        List<Object> params = new ArrayList<>();
        if (StringUtils.isNotEmpty(key)) {
            sql.append(" and tag_name like ?");
            params.add("%" + key + "%");
        }

        StringBuffer totalSql = new StringBuffer("select count(1) from (" + sql.toString() + ") a");
        Integer total = jdbcTemplate.queryForObject(totalSql.toString(), params.toArray(), Integer.class);

        int startIndex = (pageNo - 1) * pageSize;
        sql.append(" limit ?,?");
        params.add(startIndex);
        params.add(pageSize);
        List<Tag> tags = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<Tag>(Tag.class));
        result.setTotal(total);
        result.setItems(tags);
        return result;
    }

    @Override
    public Tag create(Tag tag) {
        tag.setCreateTime(new Date());
        return tagRepository.save(tag);
    }

    @Override
    public Tag update(Integer id, Tag tag) throws Exception {
        Tag tempTag = tagRepository.getOne(id);
        if (tempTag == null) {
            throw new ServiceException("不存在【" + id + "】对应的标签");
        }
        tempTag.setTagName(tag.getTagName());
        tempTag.setTagDesc(tag.getTagDesc());
        return tagRepository.save(tempTag);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Tag tempTag = tagRepository.getOne(id);
        if (tempTag == null) {
            throw new ServiceException("不存在【" + id + "】对应的标签");
        }
        tagRepository.delete(tempTag);
    }

    @Override
    public Tag get(Integer id) throws Exception {
        Tag tempTag = tagRepository.getOne(id);
        if (tempTag == null) {
            throw new ServiceException("不存在【" + id + "】对应的标签");
        }
        return tempTag;
    }
}
