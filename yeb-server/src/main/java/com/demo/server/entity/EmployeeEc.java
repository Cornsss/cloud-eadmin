package com.demo.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhul
 * @since 2021-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_employee_ec")
@ApiModel(value="EmployeeEc对象", description="")
public class EmployeeEc implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "员工编号")
    private Integer eid;

    @ApiModelProperty(value = "奖罚日期")
    private Date ecDate;

    @ApiModelProperty(value = "奖罚原因")
    private String ecReason;

    @ApiModelProperty(value = "奖罚分")
    private String ecPoint;

    @ApiModelProperty(value = "奖罚类别，0：奖，1：罚")
    private String ecType;

    @ApiModelProperty(value = "备注")
    private String remark;


}
