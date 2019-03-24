package com.yhr.course.course.vo;

import lombok.Data;
import org.apache.catalina.LifecycleState;

import java.util.List;

/**
 * Created by Administrator on 2019/3/23.
 */

@Data
public class ChapterTreeVo {

    private String label;

    private Integer value;

    private List<ChapterTreeVo> children;


}
