package com.yhr.course.course.utils;

import lombok.Data;

import java.util.Collection;

/**
 * Created by Administrator on 2019-01-10.
 */

@Data
public class PagerHelper<T> {

    private Integer total;

    private Collection<T> items;

}
