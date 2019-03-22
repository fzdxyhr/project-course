package com.yhr.course.course.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2018/12/6 0006.
 */

@Data
public class AnswerVo {

    @ApiModelProperty("回复标识")
    private Integer id;

    @ApiModelProperty("回复内容")
    private String answerContent;

    @ApiModelProperty("回复问题标识")
    private Integer questionId;

    @ApiModelProperty("用户标识")
    private Integer userId;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("用户头像地址")
    private String userPhotoPath;

    @ApiModelProperty("创建时间")
    private Date createTime;

}
