package com.yhr.course.course.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2018/12/6 0006.
 */

@Data
@Entity
@Table(name = "s_tag")
public class Tag {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("标签标识")
    private Integer id;

    @ApiModelProperty("标签名称")
    @Column(name = "tag_name")
    private String tagName;

    @ApiModelProperty("标签描述")
    @Column(name = "tag_desc")
    private String tagDesc;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private Date createTime;

}
